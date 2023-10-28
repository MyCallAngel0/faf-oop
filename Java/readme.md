# OOP laboratory FAF-221

## Laboratory 0
Basic laboratory task

## Laboratory 1
To create a TUM Management System

Operation types:
- Faculty operations;
- General operations;
- Student operations;
- Batch operations;
- Save/load operations.

It contains a logging system, a save/load system, a way to graduate students from the "graduate" file and add students to a faculty from a file.

All you need to do is run the main method and it all should be working as intended.

## Laboratory 2
To create a program that has similar behavior to Git

The program contains all the necessary commands for this laboratory: commit, info and status, with an addition of an exit command.
The files that are checked are stored in the git file.
For info command there are some parameters to it:
- filename : it includes the name of the file + extension (e.g python_script.py) and it prints information only about said file;
- all files : it gives information about every file present in the checked directory;
- image files : information about every image present in checked directory;
- program files : information about every program file present in checked directory;
- text files : information about every text file present in checked directory;

A separate thread was created for checking if there were any changes made in the files while the program runs. It will only output if detects a change in the files from the git directory

To run the program, you need to run the Main.java file