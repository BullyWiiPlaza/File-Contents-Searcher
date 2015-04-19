A simple command line utility which reads all files in a directory (including all subdirectories) and tries to find the search term supplied. If it is found, the absolute path of the matching files will be printed onto the console and into the `output.txt` file. The latter is generated on runtime in the same directory as this program.

**Usage:**<br>
`jar -jar "programName.jar" [targetDirectory] [searchTerm]`

**1st command line argument:**<br>
The directory to analyze

**2nd command line argument:**<br>
The search term to use

The VM argument `-Xmx10g` could be helpful to supply Java with 10 GB of heap space in order to not run out too quickly.
