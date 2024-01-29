package at.opr.uebung09;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Snake extends Application {

	private static final double WIDTH = 600;
	private static final double HEIGHT = WIDTH;
	private double rows;
	private double columns;
	private int snakeStartX;
	private int snakeStartY;

	int speed = 600;
	int gameMode = 0;
	private List<Position> snakeBody = new ArrayList<Position>();
	private Position snakeHead;
	GraphicsContext gc;
	int foodX;
	int foodY;
	int stoneXOne = -1;
	int stoneYOne = -1;
	int stoneXTwo = -1;
	int stoneYTwo = -1;
	int stoneXThree = -1;
	int stoneYThree = -1;
	int stoneXFour = -1;
	int stoneYFour = -1;
	int stoneXFive = -1;
	int stoneYFive = -1;
	int stoneXSix = -1;
	int stoneYSix = -1;
	int stoneXSeven = -1;
	int stoneYSeven = -1;
	int stoneXEight = -1;
	int stoneYEight = -1;
	int poisonFoodX = -1;
	int poisonFoodY = -1;
	int poisonFoodXExtrem = -1;
	int poisonFoodYExtrem = -1;
	private static boolean isPaused = false;

	private int d = RIGHT;
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;

	/**
	 * first Stage shown
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(" Snake ");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);

		Label space = new Label(" ");

		Button game = new Button();
		game.setText("Game");

		Button preferences = new Button();
		preferences.setText("Preferences");

		dropDown(game, preferences, primaryStage);

		HBox buttonContainer = new HBox(10);
		buttonContainer.getChildren().addAll(space, game, preferences);

		Image image = new Image("snake.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(WIDTH / 2); // Neues Bildbreite
		imageView.setFitHeight(HEIGHT / 2); // Neues Bildhöhe

		VBox topContainer = new VBox(10);
		topContainer.getChildren().addAll(buttonContainer, new Separator());

		root.setTop(topContainer);
		root.setCenter(imageView);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Drop-Down Menu and setOnAction for Drop-Down
	 */
	public void dropDown(Button game, Button preferences, Stage primaryStage) {
		ContextMenu dropDownMenu = new ContextMenu();
		MenuItem one = new MenuItem("Start Game");
		MenuItem two = new MenuItem("Exit");
		dropDownMenu.getItems().addAll(one, two);

		game.setOnAction(event -> {
			Bounds bounds = game.localToScreen(game.getBoundsInLocal());
			dropDownMenu.show(game, bounds.getMinX(), bounds.getMaxY());
		});

		ContextMenu dropDownMenuTwo = new ContextMenu();
		MenuItem three = new MenuItem("Game Speed");
		MenuItem four = new MenuItem("Game Mode");
		dropDownMenuTwo.getItems().addAll(three, four);

		preferences.setOnAction(event -> {
			Bounds bounds = preferences.localToScreen(preferences.getBoundsInLocal());
			dropDownMenuTwo.show(preferences, bounds.getMinX(), bounds.getMaxY());
		});

		one.setOnAction(event -> {
			primaryStage.close();
			startGame(primaryStage);
		});

		two.setOnAction(event -> {
			primaryStage.close();
		});

		three.setOnAction(event -> {
			gameSpeed();
		});

		four.setOnAction(event -> {
			gameMode();
		});
	}

	/**
	 * Textfield to declarate rows, columns, ...
	 */
	public TextField field() {
		TextField field = new TextField();
		field.setEditable(true);
		field.setPrefColumnCount(10);

		field.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.contains("\n")) {
				field.setText(oldValue); // Wenn eine neue Zeile eingegeben wurde, wird der vorherige Wert
											// wiederhergestellt
			}
		});
		return field;
	}

	/**
	 * Button "Start Game"
	 */
	public void startGame(Stage primaryStage) {
		Stage newStage = new Stage();
		newStage.setTitle("Start Game");

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);

		Label txt = new Label("  Please specify the game's configurations.");

		HBox top = new HBox();
		top.getChildren().addAll(txt);

		VBox specify = new VBox(10);
		HBox rowOne = new HBox(165);
		HBox rowTwo = new HBox(146);
		HBox rowThree = new HBox(43);
		HBox rowFour = new HBox(4);

		Label rowsLabel = new Label("  Rows [>= 10]");
		Label columnsLabel = new Label("  Columns [>= 10]");
		Label iRow = new Label("  Initial row of snake [> 0 && < Rows]");
		Label iColumn = new Label("  Initial column of snake [> 0 && < Columns]");

		TextField rowsText = field();
		TextField columnsText = field();
		TextField startXText = field();
		TextField startYText = field();

		rowOne.getChildren().addAll(rowsLabel, rowsText);
		rowTwo.getChildren().addAll(columnsLabel, columnsText);
		rowThree.getChildren().addAll(iRow, startXText);
		rowFour.getChildren().addAll(iColumn, startYText);

		specify.getChildren().addAll(rowOne, rowTwo, rowThree, rowFour);

		HBox bottom = new HBox(5);
		bottom.setAlignment(Pos.CENTER_RIGHT);

		Button cancel = new Button("Cancel");
		Button start = new Button("Start");
		Label space = new Label(" ");

		bottom.getChildren().addAll(cancel, start, space);
		root.setBottom(bottom);

		VBox container = new VBox(10);
		container.getChildren().addAll(top, new Separator(), specify, bottom);

		root.setTop(container);

		cancel.setOnAction(event -> {
			newStage.close();
		});

		start.setOnAction(event -> {
			rows = convertToInt(getString(rowsText));
			columns = convertToInt(getString(columnsText));
			snakeStartX = convertToInt(getString(startXText));
			snakeStartY = convertToInt(getString(startYText));

			if (rows >= 10 && columns >= 10 && snakeStartX > 0 && snakeStartX < rows && snakeStartY > 0
					&& snakeStartY < columns) {
				newStage.close();
				snake(primaryStage);
			} else {
				Stage defaultStage = new Stage();
				defaultStage.setTitle("False Input");
				Label info = new Label("Please enter correct Input");
				BorderPane tmp = new BorderPane();

				root.setCenter(info);

				Scene defaultScene = new Scene(tmp, WIDTH, HEIGHT);

				defaultStage.setScene(defaultScene);
			}
		});

		newStage.setScene(scene);
		newStage.show();
	}

	public String getString(TextField field) {
		StringBuilder s = new StringBuilder();
		s.append(field.getText());
		return s.toString();
	}

	public int convertToInt(String s) {
		int number = 0;
		int j = 0;
		int ten = 1;

		for (int i1 = s.length() - 1; i1 >= 0; i1--) {
			for (int k = 0; k < j; k++) {
				ten *= 10;
			}
			number += (s.charAt(i1) - 48) * ten;
			j++;
			ten = 1;
		}

		return number;
	}

	public void gameMode() {
		Stage newStage = new Stage();
		newStage.setTitle(" Game Mode ");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH / 2, HEIGHT / 3);

		VBox vbox = new VBox(10);
		HBox hbox = new HBox(10);
		Label game = new Label("Choose your Game Mode");
		Label space = new Label(" ");
		Label spaceTwo = new Label(" ");
		TextField choose = field();
		Label mode1 = new Label("  1: normal");
		Label mode2 = new Label("  2: one poisoned food");
		Label mode3 = new Label("  3: two poisoned food at the same time");
		hbox.getChildren().addAll(space, game, choose);

		Button done = new Button("Done");
		HBox bottom = new HBox(5);
		bottom.setAlignment(Pos.CENTER_RIGHT);
		bottom.getChildren().addAll(done, spaceTwo);

		vbox.getChildren().addAll(hbox, new Separator(), mode1, mode2, mode3, bottom);

		root.setCenter(vbox);

		done.setOnAction(event -> {
			gameMode = convertToInt(getString(choose));
			if (gameMode <= 3 && gameMode >= 1) {
				newStage.close();
			} else {
				Stage defaultStage = new Stage();
				defaultStage.setTitle("False Input");
				Label info = new Label("  Please enter correct Input");
				BorderPane tmp = new BorderPane();

				HBox bottomFail = new HBox(5);
				Label spaceThree = new Label(" ");
				bottomFail.setAlignment(Pos.CENTER_RIGHT);
				bottomFail.getChildren().addAll(done, spaceThree);

				VBox vboxFail = new VBox(10);
				vboxFail.getChildren().addAll(new Sphere(), info, bottomFail);

				root.setCenter(vboxFail);

				Scene defaultScene = new Scene(tmp, WIDTH, HEIGHT);

				defaultStage.setScene(defaultScene);

				done.setOnAction(e -> {
					newStage.close();
					gameMode();
				});
			}

		});

		newStage.setScene(scene);
		newStage.show();
	}

	/**
	 * Button "Game Speed"
	 */
	public void gameSpeed() {
		Stage newStage = new Stage();
		newStage.setTitle("Game Speed Configurations");

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);

		Label txt = new Label("  Please specify the game's speed in ms/step.");

		Slider slider = new Slider(200, 1000, 600);

		Label cSpeed = new Label("  Current Speed:");
		Label ms = new Label("ms");
		Label valueLabel = new Label("500");

		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			speed = newValue.intValue();
			valueLabel.setText("" + speed);
		});

		HBox speed = new HBox(5);
		speed.getChildren().addAll(cSpeed, valueLabel, ms);

		HBox bottom = new HBox(5);
		bottom.setAlignment(Pos.CENTER_RIGHT);

		Button cancel = new Button("Cancel");
		Button done = new Button("Done");
		Label space = new Label(" ");
		Label spaceTwo = new Label(" ");

		bottom.getChildren().addAll(cancel, done, space);
		root.setBottom(bottom);

		VBox container = new VBox(10);
		container.getChildren().addAll(txt, new Separator(), speed, slider, spaceTwo, bottom);

		root.setTop(container);

		cancel.setOnAction(event -> {
			valueLabel.setText("600");
			newStage.close();
		});

		done.setOnAction(event -> {
			newStage.close();
		});

		newStage.setScene(scene);
		newStage.show();
	}

	public void generateFood() {
		start: while (true) {
			foodX = (int) (Math.random() * columns);
			foodY = (int) (Math.random() * rows);
			if (gameMode >= 2) {
				poisonFoodX = (int) (Math.random() * columns);
				poisonFoodY = (int) (Math.random() * rows);
			}
			if (gameMode == 3) {
				poisonFoodXExtrem = (int) (Math.random() * columns);
				poisonFoodYExtrem = (int) (Math.random() * rows);
			}

			for (Position snake : snakeBody) {
				if (snake.getX() == foodX && snake.getY() == foodY) {
					continue start;
				}
				if (gameMode >= 2) {
					if (snake.getX() == poisonFoodX && snake.getY() == poisonFoodY) {
						continue start;
					}
				}
				if (gameMode == 3) {
					if (snake.getX() == poisonFoodXExtrem && snake.getY() == poisonFoodYExtrem) {
						continue start;
					}
				}

			}
			break;
		}
	}

	public void drawStone(GraphicsContext gc) {
		int stoneX = (int) (Math.random() * columns);
		int stoneY = (int) (Math.random() * rows);

		if (snakeBody.size() == 5 && stoneXOne == -1 && stoneYOne == -1) {
			stoneXOne = stoneX;
			stoneYOne = stoneY;
		} else if (snakeBody.size() == 10 && stoneXTwo == -1 && stoneYTwo == -1) {
			stoneXTwo = stoneX;
			stoneYTwo = stoneY;
		} else if (snakeBody.size() == 15 && stoneXThree == -1 && stoneYThree == -1) {
			stoneXThree = stoneX;
			stoneYThree = stoneY;
		} else if (snakeBody.size() == 20 && stoneXFour == -1 && stoneYFour == -1) {
			stoneXFour = stoneX;
			stoneYFour = stoneY;
		} else if (snakeBody.size() == 25 && stoneXFive == -1 && stoneYFive == -1) {
			stoneXFive = stoneX;
			stoneYFive = stoneY;
		} else if (snakeBody.size() == 30 && stoneXSix == -1 && stoneYSix == -1) {
			stoneXSix = stoneX;
			stoneYSix = stoneY;
		} else if (snakeBody.size() == 35 && stoneXSeven == -1 && stoneYSeven == -1) {
			stoneXSeven = stoneX;
			stoneYSeven = stoneY;
		} else if (snakeBody.size() == 40 && stoneXEight == -1 && stoneYEight == -1) {
			stoneXEight = stoneX;
			stoneYEight = stoneY;
		}

		if (snakeBody.size() >= 5) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXOne * (WIDTH / columns) + 7, stoneYOne * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
		if (snakeBody.size() >= 10) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXTwo * (WIDTH / columns) + 7, stoneYTwo * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
		if (snakeBody.size() >= 15) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXThree * (WIDTH / columns) + 7, stoneYThree * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
		if (snakeBody.size() >= 20) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXFour * (WIDTH / columns) + 7, stoneYFour * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
		if (snakeBody.size() >= 25) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXFive * (WIDTH / columns) + 7, stoneYFive * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
		if (snakeBody.size() >= 30) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXSix * (WIDTH / columns) + 7, stoneYSix * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
		if (snakeBody.size() >= 35) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXSeven * (WIDTH / columns) + 7, stoneYSeven * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
		if (snakeBody.size() >= 40) {
			gc.setFill(Color.GRAY);
			gc.fillOval(stoneXEight * (WIDTH / columns) + 7, stoneYEight * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
					(HEIGHT / rows) - 14);
		}
	}

	public void drawFood(GraphicsContext gc) {
		gc.setFill(Color.LIMEGREEN);
		gc.fillOval(foodX * (WIDTH / columns) + 7, foodY * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
				(HEIGHT / rows) - 14);
	}

	public void drawPoisonFood(GraphicsContext gc) {
		gc.setFill(Color.FIREBRICK);
		gc.fillOval(poisonFoodX * (WIDTH / columns) + 7, poisonFoodY * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
				(HEIGHT / rows) - 14);
		if (gameMode == 3) {
			gc.setFill(Color.FIREBRICK);
			gc.fillOval(poisonFoodXExtrem * (WIDTH / columns) + 7, poisonFoodYExtrem * (HEIGHT / rows) + 7,
					(WIDTH / columns) - 14, (HEIGHT / rows) - 14);
		}
	}

	/**
	 * Game Stage
	 */
	public void snake(Stage primaryStage) {
		Stage snakeStage = new Stage();
		snakeStage.setTitle(" Snake ");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH + WIDTH / columns * 2, HEIGHT + HEIGHT / rows * 3);

		Label space = new Label(" ");

		Button game = new Button();
		game.setText("Game");

		Button preferences = new Button();
		preferences.setText("Preferences");

		dropDown(game, preferences, snakeStage);

		HBox buttonContainer = new HBox(10);
		buttonContainer.getChildren().addAll(space, game, preferences);

		Label pointsText = new Label("  Points:");
		Label points = new Label("1");
		Label info = new Label("Game info");

		Canvas gameField = new Canvas(WIDTH, HEIGHT);

		gc = gameField.getGraphicsContext2D();

		HBox bottom = new HBox(10);
		bottom.getChildren().addAll(pointsText, points, info);
		bottom.setAlignment(Pos.BOTTOM_LEFT);

		VBox topContainer = new VBox(10);
		topContainer.setAlignment(Pos.CENTER);
		topContainer.getChildren().addAll(buttonContainer, new Separator(), gameField, new Separator(), bottom);

		root.setCenter(topContainer);

		snakeStage.setScene(scene);
		snakeStage.show();

		snakeBody.add(new Position(snakeStartX, snakeStartY));

		snakeHead = snakeBody.get(0);
		generateFood();

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
				if (d != LEFT) {
					d = RIGHT;
				}
			} else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
				if (d != RIGHT) {
					d = LEFT;
				}
			} else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
				if (d != DOWN) {
					d = UP;
				}
			} else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
				if (d != UP) {
					d = DOWN;
				}
			} else if (e.getCode() == KeyCode.P) {
				togglePause();
			}
		});

		if (snakeBody.size() >= 5) {
			drawStone(gc);
		}

		Timeline tl = new Timeline(
				new KeyFrame(Duration.millis(speed), e -> run(gc, snakeStage, points, info, primaryStage, gameField)));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
	}

	private static void togglePause() {
		isPaused = !isPaused;
	}

	/**
	 * game
	 */
	public void run(GraphicsContext gc, Stage snakeStage, Label points, Label info, Stage primaryStage,
			Canvas gameField) {
		if (!isPaused) {
			info.setText("Control with W-A-S-D. Press P to pause the Game.");
			points.setText("" + snakeBody.size());

			drawBackground(gc);
			drawFood(gc);
			if (gameMode == 2) {
				drawPoisonFood(gc);
			} else if (gameMode == 3) {
				drawPoisonFood(gc);
				drawPoisonFood(gc);
			}

			if (snakeBody.size() >= 5) {
				drawStone(gc);
			}
			if (snakeBody.size() >= 10) {
				drawStone(gc);
			}
			if (snakeBody.size() >= 15) {
				drawStone(gc);
			}
			if (snakeBody.size() >= 20) {
				drawStone(gc);
			}
			if (snakeBody.size() >= 25) {
				drawStone(gc);
			}
			if (snakeBody.size() >= 30) {
				drawStone(gc);
			}
			if (snakeBody.size() >= 35) {
				drawStone(gc);
			}
			if (snakeBody.size() >= 40) {
				drawStone(gc);
			}

			draw(gc);

			for (int i = snakeBody.size() - 1; i >= 1; i--) {
				snakeBody.get(i).x = snakeBody.get(i - 1).x;
				snakeBody.get(i).y = snakeBody.get(i - 1).y;
			}

			int x = snakeHead.x;
			int y = snakeHead.y;

			switch (d) {
			case RIGHT:
				snakeHead.x++;
				break;
			case LEFT:
				snakeHead.x--;
				break;
			case UP:
				snakeHead.y--;
				break;
			case DOWN:
				snakeHead.y++;
				break;
			}

			if (snakeHead.x == -1 && d == LEFT) {
				snakeHead.x = (int) columns - 1;
			}
			if (snakeHead.x == columns && d == RIGHT) {
				snakeHead.x = 0;
			}
			if (snakeHead.y == -1 && d == UP) {
				snakeHead.y = (int) rows - 1;
			}
			if (snakeHead.y == rows && d == DOWN) {
				snakeHead.y = 0;
			}

			for (int i = 1; i < snakeBody.size(); i++) {
				if (snakeBody.get(i).x == snakeHead.x && snakeBody.get(i).y == snakeHead.y) {
					togglePause();
					gameOver(primaryStage, gameField);
					return;

				}
			}

			if (snakeHead.x == poisonFoodX && snakeHead.y == poisonFoodY && snakeBody.size() == 1) {
				snakeBody.remove(snakeBody.size() - 1);
				if ((x + y) % 2 == 0) {
					gc.setFill(Color.web("fae7ae"));
				} else {
					gc.setFill(Color.web("fff7d3"));
				}
				gc.fillRect(x * (WIDTH / columns), y * (HEIGHT / rows), (WIDTH / columns), (HEIGHT / rows));
				draw(gc);
				togglePause();
				points.setText("0");
				gameOver(primaryStage, gameField);
				return;
			} else if (snakeHead.x == poisonFoodXExtrem && snakeHead.y == poisonFoodYExtrem && snakeBody.size() == 1) {
				snakeBody.remove(snakeBody.size() - 1);
				if ((x + y) % 2 == 0) {
					gc.setFill(Color.web("fae7ae"));
				} else {
					gc.setFill(Color.web("fff7d3"));
				}
				gc.fillRect(x * (WIDTH / columns), y * (HEIGHT / rows), (WIDTH / columns), (HEIGHT / rows));
				draw(gc);
				togglePause();
				points.setText("0");
				gameOver(primaryStage, gameField);
				return;
			}

			if (snakeBody.size() >= 5) {
				if (snakeHead.x == stoneXOne && snakeHead.y == stoneYOne
						|| snakeHead.x == stoneXTwo && snakeHead.y == stoneYTwo
						|| snakeHead.x == stoneXThree && snakeHead.y == stoneYThree
						|| snakeHead.x == stoneXFour && snakeHead.y == stoneYFour
						|| snakeHead.x == stoneXFive && snakeHead.y == stoneYFive
						|| snakeHead.x == stoneXSix && snakeHead.y == stoneYSix
						|| snakeHead.x == stoneXSeven && snakeHead.y == stoneYSeven
						|| snakeHead.x == stoneXEight && snakeHead.y == stoneYEight) {
					togglePause();
					points.setText("0");
					gameOver(primaryStage, gameField);
					return;
				}
			}

			if (snakeHead.x == foodX && snakeHead.y == foodY) {
				snakeBody.add(new Position(foodX, foodY));
				generateFood();
			}
			if (gameMode >= 2) {
				if (snakeHead.x == poisonFoodX && snakeHead.y == poisonFoodY) {
					snakeBody.remove(snakeBody.size() - 1);
					generateFood();
				}
			}
			if (gameMode == 3) {
				if (snakeHead.x == poisonFoodXExtrem && snakeHead.y == poisonFoodYExtrem) {
					snakeBody.remove(snakeBody.size() - 1);
					generateFood();
				}
			}

		} else {
			info.setText("Game is paused. Press P to resume.");
		}
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(Color.TEAL);

		gc.fillRoundRect(snakeHead.x * (WIDTH / columns) + 7, snakeHead.y * (HEIGHT / rows) + 7, (WIDTH / columns) - 14,
				(HEIGHT / rows) - 14, 20, 20);

		for (int i = 1; i < snakeBody.size(); i++) {
			gc.fillRoundRect(snakeBody.get(i).getX() * ((WIDTH / columns)) + 7,
					snakeBody.get(i).getY() * ((HEIGHT / rows)) + 7, (WIDTH / columns) - 14, (HEIGHT / rows) - 14, 20,
					20);
		}
	}

	/**
	 * background of the game
	 */
	public void drawBackground(GraphicsContext gc) {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if ((i + j) % 2 == 0) {
					gc.setFill(Color.web("fae7ae"));
				} else {
					gc.setFill(Color.web("fff7d3"));
				}
				gc.fillRect(j * (WIDTH / columns), i * (HEIGHT / rows), (WIDTH / columns), (HEIGHT / rows));
			}
		}
	}

	public void gameOver(Stage primaryStage, Canvas gameField) {
		Stage snakeStage = new Stage();
		snakeStage.setTitle(" Game Over ");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH / 2, HEIGHT / 2);
		Label end = new Label("  Your Points: " + snakeBody.size());
		Label smile = null;
//		Label space = new Label(" ");;

		if (snakeBody.size() > 20) {
			smile = new Label("  really good :D ");
		} else if (snakeBody.size() < 10) {
			smile = new Label("  this could be better :( ");
		} else {
			smile = new Label("  not that bad :) ");
		}

//		Button home = new Button();
//		home.setAlignment(Pos.CENTER_RIGHT);
//		ImageView image = new ImageView(new Image("home.png"));
//		image.setFitWidth(30);
//		image.setFitHeight(30);
//		
//		home.setGraphic(image);

		VBox v = new VBox(10);
		v.setAlignment(Pos.CENTER);
		if (smile != null) {
			v.getChildren().addAll(end, smile);
		}

//		home.setOnAction(event -> {
//			snakeStage.close();
//			try {
//				start(primaryStage);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});

		root.setCenter(v);

		snakeStage.setScene(scene);
		snakeStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
