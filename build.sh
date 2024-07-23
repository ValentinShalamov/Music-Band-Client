#Create jar-file
javac -d build/compile -cp lib/gson-2.11.0.jar:src/ src/Main.java
cd build/compile
jar cfe ../app.jar Main ./*/*.class Main.class
