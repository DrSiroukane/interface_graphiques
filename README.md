# Interface graphiques

## Tp 1 :

Used classes : 

* BorderBane : layout use different positions (Top, Left, Center, Right, Bottom),
* ImageView : Image scene to hold and show image on it,
* Scene : Main scene for application window to show the root layout,
* setBackground method,

Some interesting instruction :
```$xslt
pane.setBackground(new Background(
    new BackgroundFill(Color.web("#383838"), null, null)));
pane.setMinWidth(300);
pane.setMaxWidth(900);
pane.setMinHeight(200);
pane.setMaxHeight(500);
ImageView imageView = new ImageView(
    new Image(getClass().getResource("image.jpg").toExternalForm()));
```

## Tp 2 :

Used classes :

* MenuBar : bar to hold menus, position on top of window,
* Menu : items holder,
* MenuItem : item special for menu,
* RadioMenuItem : item special for menu type Radio,
* FileChooser : file picker,
* KeyCode : hold all keys (Q, W, E, R, T, ...),
* KeyCombination : hold static standard keys (Ctrl, Alt, ...),
* KeyCodeCombination : create a shortcut combination,

Some interesting instruction :
```$xslt
MenuBar menuBar = new MenuBar();
Menu fileMenu = new Menu("File");
MenuItem openItem = new MenuItem("Open"); 
RadioMenuItem editRadioItem = new RadioMenuItem("Edit");  
openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN)); 
fileMenu.getItems().addAll(openItem, saveItem, exitItem);
menuBar.getMenus().addAll(fileMenu, editMenu);

FileChooser fileChooser = new FileChooser();
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("List files", "*.lst"),
    new FileChooser.ExtensionFilter("All files", "*.*")
);
exitItem.setOnAction(e -> {
    System.exit(0);
});
```

## Tp 3 :

Used classes :

* Pos : hold static position (CENTER, CENTER_RIGHT, CENTER_LEFT, ...),
* VBox : layout position its children vertically,

Some interesting instruction :
```$xslt
VBox vBox = new VBox();
button1.setId("button1");
vBox.getChildren().addAll(button1, button2, button3, button4);
vBox.setAlignment(Pos.CENTER);
vBox.setSpacing(10);
scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
```

## Tp 4 :

Used classes :

* HBox : layout position its children horizontally,
* StackPane : layout that position node on center,
* WebView : page web viwer,
* WebEngine : web view page load handler,
* WebHistory : web view history handler,

Some interesting instruction :
```$xslt
webView = new WebView();
webEngine = webView.getEngine();
webHistory = webEngine.getHistory();

urlTextField.setOnKeyPressed(ke -> ... );
webEngine.getLoadWorker().stateProperty().addListener(
    (ov, oldState, newState) -> {
    	if (newState == Worker.State.SUCCEEDED) { ... }
	}
);

int history_size = webHistory.getEntries().size();
int current_index = webHistory.getCurrentIndex();
webHistory.go(-1);
webHistory.go(+1);
```

## Tp 5 :

Used classes :

* GridPane : layout position its nodes depend coordination of Grid,
* Rectangle : shape rectangle,
* Circle : shape circle,
* RotateTransition : rotation animation,
* ScaleTransition : zoom in/out animation,

Some interesting instruction :
```$xslt
Rectangle rectangle = new Rectangle(size, size);
Circle circle = new Circle(size / 2);
circle.setFill(COLORS[color]);

scaleTransition.setByX(zoom_value);
scaleTransition.setByY(zoom_value);

rotateTransition = new RotateTransition(Duration.millis(speed), rectangle);
rotateTransition.setByAngle(rotate_angle);
rotateTransition.setCycleCount(Timeline.INDEFINITE);
rotateTransition.setAutoReverse(true);

rotateTransition.play();
rotateTransition.pause();
```

## Tp 6 :

Used classes :

* Timeline : time thread helper play, stop, set duration, catch on finish,

Some interesting instruction :
```$xslt
Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2)));
timeline.play();
timeline.stop();
timeline.setOnFinished(evt -> ... );
game_time = System.currentTimeMillis();
button.setStyle(
	"-fx-background-image: url('" + getClass().getResource("images/Blank.png").toExternalForm() + "');"
);
```

## Tp 7 :

Used classes :

* Slider : slider controller,
* ColorPicker : color picker controller,

Some interesting instruction :
```$xslt
Slider slider = new Slider();
slider.setMax(200);
slider.setShowTickMarks(true);
slider.setShowTickLabels(true);
slider.setMajorTickUnit(50);
slider.setOrientation(Orientation.VERTICAL);

ColorPicker colorPicker = new ColorPicker();
colorPicker.setValue(Color.GREEN);

slider.valueProperty().addListener(
        (ov, oldVal, newVal) -> {
            circle.setRadius(newVal.doubleValue() / 2);
        }
);

circle.radiusProperty().bind(slider.valueProperty().divide(2));
```

## Tp 8 :

* Pane : layout most time used to put element on specified position or draw on it,
* RadioButton : Label with checkbox,
* ToggleGroup : group that collect many RadioButtons to check only one,

Some interesting instruction :
```$xslt
ToggleGroup Shapes = new ToggleGroup();
 RadioButton circleRadioButton = new RadioButton("Circle");
circleRadioButton.setSelected(true); // selected
circleRadioButton.setToggleGroup(Shapes);
pane.setOnMouseClicked(e -> ... );
if (addRadioButton.isSelected()) { ... }
circle.setCenterX(e.getX());
circle.setCenterY(e.getY());
rectangle.setX(e.getX() - 50);
rectangle.setY(e.getY() - 50);
```

## Tp 9 :

Used classes :

* TextFormatter : check holder used to check input on TextFeild as validator,

Some interesting instruction :
```$xslt
TextFormatter<String> uppercaseTextFormatter = new TextFormatter<String>(
        change -> {
            String text = change.getText();
            if ((0 != text.length()) && !text.matches("[a-zA-Z]") ) {
                return null;
            }
            change.setText(text.toUpperCase());
            return change;
        }
);

typeTextField.setTextFormatter(uppercaseTextFormatter);
```

## Tp 10 :

## Tp 11 :

