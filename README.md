
This repo is created when I started learning Scala by following this course -   
[Functional Programming Principles in Scala](https://www.coursera.org/learn/progfun1/home/welcome) which is instructed by Martin Odersky, the Designer of Scala.

**Prerequisites to use this project**

 1. [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 2. [Scala IDE for Eclipse](http://scala-ide.org/download/current.html)
 3. [sbt](https://www.scala-sbt.org/1.0/docs/Setup.html)

**Run these commands to setup the project for Eclipse** (One Time Process)
```
 git clone https://github.com/RamvigneshPasupathy/scala-playground.git
 cd scala-playground
 sbt eclipse
```
This will fetch all the dependencies required for the project. And all the needed Eclipse files will be created.

**Opening the Project in Eclipse**

 1. Start up Eclipse
 2. Select "File" - "Import" from the menu
 3. In the folder "General", select the item "Existing Projects into Workspace" and click "Next >"
 4. The text field "Select root directory" asks for the root folder of your project - the root folder is where the `build.sbt` file is located
 5. Click "Finish"
