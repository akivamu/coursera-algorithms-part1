# Compile and run

```
# cd to root of project

# set up class path
export CLASSPATH=`pwd`/libs/algs4.jar
export ROOTPATH=`pwd`

# compile
javac ${ROOTPATH}/src/week1/percolation/Percolation.java

cd src
java -cp "${ROOTPATH}/libs/algs4.jar": week1/percolation/Percolation < ${ROOTPATH}/input/week1/percolation/input2.txt
```