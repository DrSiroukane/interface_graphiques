# Interface graphiques

##Tp 1 :
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

##Tp 2 :
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

##Tp 3 :
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







