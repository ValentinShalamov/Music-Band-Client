#Create jar-file
javac -d build/compile -cp src/ src/Main.java
cd build/compile
jar cfe ../app.jar Main ./*/*.class Main.class

