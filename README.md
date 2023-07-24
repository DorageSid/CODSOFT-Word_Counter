# Word Counter

**Description**

The **Word Counter** is a simple Java program that allows users to count the total number of words and the number of unique words in a given text or a file.

#### Prerequisites
To run the Word Counter application, you need to have the following:

-  Java Development Kit (JDK) installed on your system.
-  An Integrated Development Environment (IDE) or a text editor to open and run the Java code.

## How to Run the Project
#### To run the Word Counter project, follow these steps:

1. Clone the repository to your local machine using the following command:
  -  git clone https://github.com/DorageSid/CODSOFT-Word_Counter.git
2. Navigate to the project directory:
  -  cd -project directory-
3. Compile the Java source code:
  -  javac -file name.java-
4. Run the application:
  -  java -file name-

#### How to Use
After running the project
#### To count words in the entered text:
-  Type or paste your text into the provided text area.
-  Click the "Count Words" button.
-  The word count and unique word count will be displayed on the right side of the window.

#### To count words in a file:
-  Click the "Open File" button.
-  Select the text file you want to analyze.
-  The content of the file will be displayed in the text area, and the word count and unique word count will be updated accordingly.


#### Technology Used
`Java` : The game is developed using the Java programming language.

`Swing`: The graphical user interface (GUI) is created using the Swing library, allowing for a simple and interactive design.

#### Features

-  Count the total number of words in the input text.
-  Count the number of unique words in the input text (excluding common stop words).
-  Allow users to open a text file and analyze its content.
-  Automatically ignore common stop words during word counting.
-  Display word count and unique word count on the GUI.

<font color="#FFFF00">Customize Stop Words</font>

The application includes a set of common stop words that are excluded during word counting. If you want to customize this set or add more stop words, you can `modify`  the **stopWords** set in the **WordCounter** class.
