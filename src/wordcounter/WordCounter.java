import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordCounter extends JFrame implements ActionListener {

    private final JTextArea textArea;
    private final JButton countButton;
    private final JButton openFileButton;
    private final JLabel wordCountLabel;
    private final JLabel uniqueWordCountLabel;
    private final JLabel statusLabel;
    private final Set<String> stopWords;

    public WordCounter() {
        setTitle("Word Counter");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create GUI components
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        countButton = new JButton("Count Words");
        countButton.addActionListener(this);
        openFileButton = new JButton("Open File");
        openFileButton.addActionListener(this);
        wordCountLabel = new JLabel("Word Count: ");
        uniqueWordCountLabel = new JLabel("Unique Word Count: ");
        statusLabel = new JLabel("Selected File: ");

        // Add common stop words
        stopWords = new HashSet<>();
        stopWords.add("the");
        stopWords.add("and");
        stopWords.add("is");
        stopWords.add("a");
        // Add more stop words if needed.

        // Create layout using BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(countButton);
        buttonPanel.add(openFileButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel statsPanel = new JPanel(new GridLayout(3, 1));
        statsPanel.add(wordCountLabel);
        statsPanel.add(uniqueWordCountLabel);
        statsPanel.add(statusLabel);
        mainPanel.add(statsPanel, BorderLayout.EAST);

        add(mainPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == countButton) {
            String inputText = textArea.getText();
            countWords(inputText);
        } else if (e.getSource() == openFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    String fileContent = readFile(selectedFile);
                    textArea.setText(fileContent);
                    countWords(fileContent);
                    statusLabel.setText("Selected File: " + selectedFile.getAbsolutePath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading the file.", "File Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void countWords(String inputText) {
        int wordCount = 0;
        int uniqueWordCount = 0;
        Map<String, Integer> wordFrequency = new HashMap<>();

        if (!inputText.isEmpty()) {
            // Split input text into words using space and punctuation as delimiters
            String[] words = inputText.split("[\\s\\p{Punct}]+");
            wordCount = words.length;

            // Count frequency of each word and ignore common stop words
            for (String word : words) {
                word = word.toLowerCase(); // Convert word to lowercase to make it case-insensitive
                if (!stopWords.contains(word)) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }

            // Calculate the number of unique words
            uniqueWordCount = wordFrequency.size();
        }

        // Update the labels with word count and unique word count
        wordCountLabel.setText("Word Count: " + wordCount);
        uniqueWordCountLabel.setText("Unique Word Count: " + uniqueWordCount);
    }

    private String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void main(String[] args) throws Exception {
        // Set the system look and feel to make the GUI match the platform's native appearance
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(() -> new WordCounter());
    }
}