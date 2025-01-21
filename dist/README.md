# JAR File Submission (App.jar)

Commit and push a working JAR file to this directory to receive credit.
**Make sure it is named `App.jar`.**

## Generating the JAR file

Once your code is in working order, you can create an executable JAR
file that can be run from the command line. To do so, do the following:

1. Right-click on your project in the _Package Explorer_
1. Click _Export..._
1. Expand _Java > Runnable JAR..._
1. Click _Next_
1. For Launch Configuration, look for `Main` (if there are multiple Mains,
   select the one corresponding to Project 5)
1. For Export Destination, browse to the `dist/` directory of this project,
   enter the name `App.jar`, and click _Save_
1. Click _Finish_
1. If you see a warning saying "This operation repacks referenced libraries,"
   click _OK_
1. If you see a warning saying "JAR export finished with warnings,"
   click _OK_

## Running the JAR file

To try running it in the command line, you can try out the following commands from
the `dist/` directory. Note that the puzzles folder must exist since the program
looks for the default puzzle file. If you want to make your program more robust,
you can modify your code to either accept an input file argument (so the program
can be started with any puzzle), or you can define a constant NonogramModel
instance to be used when no puzzle file is found.

### `java --module-path "path-to-javafx-lib-folder" --add-modules javafx.controls,javafx.fxml -jar App.jar`

For example,

```console
$ java --module-path "C:\Users\Ethan\Desktop\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar App.jar
```

### `java --module-path "path-to-javafx-lib-folder" --add-modules javafx.controls,javafx.fxml -jar App.jar <cell-size> `

For example,

```console
$ java --module-path "C:\Users\Ethan\Desktop\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar App.jar 20
```

## Running without the Command Line

If you want to run your program with opening terminal and typing out the entire
run command, you can create a script (Batch, PowerShell, Shell, etc.) that can
executed when double-clicked.
