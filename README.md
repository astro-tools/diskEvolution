# Introduction

This is a project to port a protoplanetary disk evolution simulation to Java.

A team of undergraduate researchers at Arizona State University
is working with Prof. Shumway in Physics and
Prof. Desch in Astrophysics to implement this simulation tool in Java.
The work is based on algorithms and Fortran codes used by Prof. Desch
and graduate students Mike Lesniak (Ph. D. 2012) and Nikhil Monga.

# Building and running the application

To build and run the project:

1. Download the source using git or from a zip file.

2. Add the required external jar files to the lib directory as described in 
   [lib/README.md](https://github.com/shumway/diskEvolution/blob/master/lib/README).

3. Run ant from the source directory.

        ant jar

4. Run with “ant run” or the run script.

        bash runApp.sh

# More information

# To import the code on Eclipse (programming software that we used for writing and modifying this code)

(Assuming that Eclipse has egit)
1. File --> Import --> Projects from Git --> URI --> (Enter the URI from the GitHub site) --> 
Select Master --> Use the Ner Project Wizard --> Finish --> New Project (Select Java Project) --> 
(Give a project name) --> Finish

Now the project is imported in Eclipse. But it may have errors (since packages like JFree chart are not installed)
Right Ckick on the project --> Properties --> Java Build Path --> Libraries --> add JARs -->



See the wiki for more infomation, https://github.com/shumway/diskEvolution/wiki.
