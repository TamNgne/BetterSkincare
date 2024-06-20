This is the guideline to execute my project:

TO LOAD DATABASE IN APP BY MySQL JDBC Connector
STEP 1: Run the SQL script 'skincaredatabase.sql' in lib/data by MySQL application (creating schema is already included in the file).
STEP 2: In the directory BetterSkincare/lib/data, add file mysql-connector-j-8.0.33.jar to your library
        if you're using Intellij, go to Project Structure/Project Setting/Modules and import the jar file then apply it.
STEP 3: Go to the src/main/java/com.betterskincare/Controller/MainController class, in connectDb method, change your MySQL name (default is 'root')
        and your password to connect to MySQL server.

TO EXECUTE THE PROGRAM
STEP 1: open class HelloApplication in src/main/java/com.betterskincare and run the class.

PS: All the related JavaFX packages are included in the zip file when pulling from GIT.