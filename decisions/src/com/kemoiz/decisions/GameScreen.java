package com.kemoiz.decisions;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen {
	final String VERT = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE
			+ ";\n" + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
			+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
			+ "uniform mat4 u_projTrans;\n" + " \n" + "varying vec4 vColor;\n"
			+ "varying vec2 vTexCoord;\n" + "void main() {\n" + " vColor = "
			+ ShaderProgram.COLOR_ATTRIBUTE + ";\n" + " vTexCoord = "
			+ ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
			+ " gl_Position = u_projTrans * "
			+ ShaderProgram.POSITION_ATTRIBUTE + ";\n" + "}";
	// This will be dumped to System.out for clarity
	final String FRAG =
	// GL ES specific stuff
	"#ifdef GL_ES\n" //
			+ "#define LOWP lowp\n" //
			+ "precision mediump float;\n" //
			+ "#else\n" //
			+ "#define LOWP \n" //
			+ "#endif\n"
			+ //
			"//texture 0\n"
			+ "uniform sampler2D u_texture;\n"
			+ "\n"
			+ "//our screen resolution, set from Java whenever the display is resized\n"
			+ "uniform vec2 resolution;\n"
			+ "\n"
			+ "//\"in\" attributes from our vertex shader\n"
			+ "varying LOWP vec4 vColor;\n"
			+ "varying vec2 vTexCoord;\n"
			+ "\n"
			+ "//RADIUS of our vignette, where 0.5 results in a circle fitting the screen\n"
			+ "const float RADIUS = 1;\n"
			+ "\n"
			+ "//softness of our vignette, between 0.0 and 1.0\n"
			+ "const float SOFTNESS = 0.85;\n"
			+ "\n"
			+ "//sepia colour, adjust to taste\n"
			+ "const vec3 SEPIA = vec3(1, 1,  0.9); \n"
			+ "\n"
			+ "void main() {\n"
			+ " //sample our texture\n"
			+ " vec4 texColor = texture2D(u_texture, vTexCoord);\n"
			+ " \n"
			+ " //1. VIGNETTE\n"
			+ " \n"
			+ " //determine center position\n"
			+ " vec2 position = (gl_FragCoord.xy / resolution.xy) - vec2(0.5);\n"
			+ " \n"
			+ " //determine the vector length of the center position\n"
			+ " float len = length(position);\n"
			+ " \n"
			+ " //use smoothstep to create a smooth vignette\n"
			+ " float vignette = smoothstep(RADIUS, RADIUS-SOFTNESS, len);\n"
			+ " \n"
			+ " //apply the vignette with 50% opacity\n"
			+ " texColor.rgb = mix(texColor.rgb, texColor.rgb * vignette, 0.1);\n"
			+ " \n"
			+ " //2. GRAYSCALE\n"
			+ " \n"
			+ " //convert to grayscale using NTSC conversion weights\n"
			+ " float gray = dot(texColor.rgb, vec3(0.699, 0.587, 0.114));\n"
			+ " \n"
			+ " //3. SEPIA\n"
			+ " \n"
			+ " //create our sepia tone from some constant value\n"
			+ " vec3 sepiaColor = vec3(gray) * SEPIA;\n"
			+ " \n"
			+ " //again we'll use mix so that the sepia effect is at 75%\n"
			+ " texColor.rgb = mix(texColor.rgb, sepiaColor, 0.88);\n"
			+ " \n"
			+ " //final colour, multiplied by vertex colour\n"
			+ " gl_FragColor = texColor * vColor;\n" + "}";
	private Stage stage;
	private OrthographicCamera camera;
	private Console console;
	private Random random;
	public static boolean failure = false;
	private SpriteBatch batch;
	private ShaderProgram shader;
	private Texture monitor;

	@Override
	public void render(float delta) {
		random = new Random();
		int y = random.nextInt(200);
		if (failure) {
			Gdx.gl.glClearColor(1f, 0.0f, 0.03f, 1);

		} else {
			if (y == 25) {
				Gdx.gl.glClearColor(0.5f, 0.0f, 0.03f, 1);
			} else {
				Gdx.gl.glClearColor(0.0f, 0.0f, 0.03f, 1);
			}
		}
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		console.tick();

		if (console.newLinePlease) {
			int o = 0;
			do {
				o++;
				camera.position.y = camera.position.y - 64;
				console.newLinePlease = false;
			} while (o < console.newlinehow);
		}
		camera.update();
		stage.act();
		stage.draw();
		batch.begin();
		batch.draw(monitor, 0, camera.position.y - 300, 800, 600);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(VERT, FRAG);

		monitor = new Texture("data/monitor2.png");

		batch = new SpriteBatch(1000, shader);

		stage = new Stage(800, 480, true, batch);
		camera = new OrthographicCamera(800, 480);
		camera.position.x = 390;
		camera.position.y = 200;
		console = new Console(stage);
		stage.setCamera(camera);
		batch.setShader(shader);
		camera.setToOrtho(false);
		// batch.setProjectionMatrix(camera.projection);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
