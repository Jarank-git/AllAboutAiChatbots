//Name of Code: All About AI ChatBots
//Name: Jaran Khalid
//Purpose: Teach people about how AI chatbots work, and ethical considerations related to them

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AI extends JFrame { //extending class AI onto a Jframe

    
    String[] questions = { //intiliasing a string list questions which holds a question at each index
        "What is an AI chatbot?",
        "How do AI chatbots generate responses?",
        "What is machine learning in the context of chatbots?",
        "Which of these is a common use for AI chatbots?",
        "What is a limitation of AI chatbots?",
        "What data do AI chatbots typically rely on?",
        "Why is transparency important in AI chatbots?",
        "What does 'NLP' stand for in AI chatbots?",
        "How can AI chatbots be ethically misused?",
        "What is a benefit of using AI chatbots in customer service?"
    };

    String[][] choices = { //intiliasing a 2 dimensional string choices list  which holds the choices for the questions at each index, with each of them being in their own list with their own indexing
        {"A software program", "A physical robot", "A human assistant", "A virtual reality tool"},
        {"Pre-written scripts", "Real-time web searches", "Pattern recognition in data", "Manual human input"},
        {"A way to clean chatbot code", "A method to help chatbots learn from data", "A tool for voice recognition", "A way to program chatbots manually"},
        {"Playing music", "Answering customer questions", "Weather reporting", "Sending emails"},
        {"Always 100% accurate", "May provide incorrect information", "Requires no data", "Can think independently"},
        {"Public databases", "Personal opinions", "Web searches", "Random guesses"},
        {"To ensure honesty about its capabilities", "To avoid data storage", "To limit chatbot performance", "To restrict its access"},
        {"Neural Language Processing", "Natural Language Programming", "Natural Language Processing", "Numeric Logic Parsing"},
        {"Spreading misinformation", "Answering FAQs", "Providing translations", "Assisting in education"},
        {"24/7 availability", "Emotion recognition", "Eliminating human jobs", "100% error-free service"}
    };

    String[] answers = { //intiliasing a string list answers which holds a question at each index
        "A software program",
        "Pattern recognition in data",
        "A method to help chatbots learn from data",
        "Answering customer questions",
        "May provide incorrect information",
        "Public databases",
        "To ensure honesty about its capabilities",
        "Natural Language Processing",
        "Spreading misinformation",
        "24/7 availability"
    };

    
    int currentQuestionIndex = 0; //creating variables that will be used in the quiz section of the app
    int score = 0;
    boolean[] answeredQuestions; //boolean list to hold which index questions are answered or not
    int questionsLength = questions.length; //variable to hold length of quesitons
    int currentValue; //int variable for current value
    MediaPlayer mediaPlayer; //setting up media player in class because it works differently in terms of settingup then regular swing

    public AI() {
        new JFXPanel(); //initialize the JavaFX at the start of the code so that it is constantly open and keeping track of whether to keep the audio playing or not

        setTitle("All About AI ChatBots"); //setting up  the mainframe, and creating the size, and layout.
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JOptionPane.showMessageDialog(null, //create an optionpane that displays the basic instructions to utilise the program completely.
            "Want to learn more about AI chatbots, follow these instructions:\n" +
            
            "1) Read the provided information and make sure to fully scroll through the main page.\n" +
            "2) Click 'Explore a world with AI' to open an animation. This will also enable the quiz option\n" +
            "3) Click 'Start Quiz' to test your knowledge, the program will close once you complete the quiz and view your results\n" +
            "4) Click 'Quit' if you want to exit early\n" +
            "(DISCLAIMER: You can only use the animation and quiz once, so be careful! If you start the quiz or animation unintentionally, relaunch the program!)"
        );

        Font specialFont1 = new Font("Times New Roman", Font.PLAIN, 15); //variable to hold a specfic font to optomize coding 
        answeredQuestions = new boolean[questions.length]; //intializing the answeredQuestions array to store information about which questions have been answered through boolean values
        
        JButton startQuizButton = new JButton("Start Quiz"); //creating the startQuiz button, setting its font, foreground, background and its status on being enabled or not.
        startQuizButton.setFont(specialFont1);
        startQuizButton.setForeground(Color.CYAN);
        startQuizButton.setBackground(Color.BLACK);
        startQuizButton.setEnabled(false);
        
        JButton quitButton = new JButton("Quit"); //creating the quitButton, setting its font, foreground, and background
        quitButton.setFont(specialFont1);
        quitButton.setForeground(Color.RED);
        quitButton.setBackground(Color.BLACK);
        
        JButton exploreAIButton = new JButton("Explore a world with AI");//creating the exploreAIButton, setting its font, foreground, and background
        exploreAIButton.setFont(specialFont1); 
        exploreAIButton.setForeground(Color.CYAN);
        exploreAIButton.setBackground(Color.BLACK);
        
        JPanel bottomPanel = new JPanel(new FlowLayout()); //creating a seperatePanel to store the 3 buttons to control functionality, setting its background and adding the buttons
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(startQuizButton);
        bottomPanel.add(quitButton);
        bottomPanel.add(exploreAIButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        JPanel informationPanel = new JPanel(new GridBagLayout()); //create a new panel which will go at the top of the mainframe that will store the information and set the background as Black
        informationPanel.setBackground(Color.BLACK);
        JScrollPane informationScrollPane = new JScrollPane(informationPanel); //turn the informationPanel into a scrollpane and attach a scrollbar to it so that it can be traversed
        informationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(informationScrollPane, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints(); //setup GBC constraints to use while placing all widgets of informationPanel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        JLabel welcomeLabel = new JLabel("Welcome to AI Learning", SwingConstants.CENTER); //create the welcomeLabel which will be a title for the informationPanel, and set the background to Gray and foreground to CYAN and set its font.
        welcomeLabel.setBackground(Color.GRAY);
        welcomeLabel.setForeground(Color.CYAN);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        gbc.gridy = 0; //setting where to place it within informationPanel
        gbc.weighty = 0.0;
        informationPanel.add(welcomeLabel, gbc); //adding it to the informationPanel.
        
        JLabel imageLabel1 = new JLabel(new ImageIcon(new ImageIcon("AI1.jpg").getImage().getScaledInstance(400, 150, java.awt.Image.SCALE_SMOOTH)));//creating a jlabel with an image icon that will display an image and add it to the informationPanel
        gbc.gridy = 1;
        informationPanel.add(imageLabel1, gbc);
        
        JTextArea textArea1 = new JTextArea( //after the image,we add this text area, set its font,foreground and background and place it underneath the first image 
            "AI chatbots are software programs that simulate human-like conversation. "
          + "They are often used for numerous tasks that involve a human mind, assisting with "
          + "information retrieval, and providing technical support. In the past couple of years, "
          + "they have been widley adopted across industries due to their ability to "
          + "handle repetitive tasks, which makes them excellent for more mundane work. "
          + "Furthermore, they are avaliable 24/7, making them a good choice for customer service as "
          + "they can provide instant responses without the need for human supervision."
        );
        textArea1.setBackground(Color.GRAY);
        textArea1.setForeground(Color.CYAN);
        textArea1.setFont(specialFont1);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea1.setEditable(false);
        gbc.gridy = 2;
        informationPanel.add(textArea1, gbc);
        
        JLabel imageLabel2 = new JLabel(new ImageIcon(new ImageIcon("AI2.jpg").getImage().getScaledInstance(400, 150, java.awt.Image.SCALE_SMOOTH))); //creating a jlabel with an image icon that will display an image and add it to the informationPanel
        gbc.gridy = 3;
        informationPanel.add(imageLabel2, gbc);
        
        JTextArea textArea2 = new JTextArea( //after the image,we add this text area, set its font,foreground and background and place it underneath the second image 
            "Responses generated by AI chatbots, are done through pattern reconigition "
          + "a process which is driven by machine learning. Machine learning is what allows chatbots to "
          + "improve over time by learning from the data it is fed and the interactions it undergoes. "
          + "Thus, it enables the chatbot to make more accurate responses and perdictions. "
          + "Additionally, chatbots often use public databases and other large datasets to "
          + "source their learning, allowing them to be knowledgeable about a great deal of topics."
        );
        textArea2.setBackground(Color.GRAY);
        textArea2.setForeground(Color.CYAN);
        textArea2.setFont(specialFont1);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setEditable(false);
        gbc.gridy = 4;
        informationPanel.add(textArea2, gbc);
        
        JLabel imageLabel3 = new JLabel(new ImageIcon(new ImageIcon("AI3.jpg").getImage().getScaledInstance(400, 150, java.awt.Image.SCALE_SMOOTH))); //creating a jlabel with an image icon that will display an image and add it to the informationPanel
        gbc.gridy = 5;
        informationPanel.add(imageLabel3, gbc);
        
        JTextArea textArea3 = new JTextArea( //after the image,we add this text area, set its font,foreground and background and place it underneath the third image 
            "However, AI chatbots are not always 100 hundred percent accurate and may provide "
          + "incorrect information or struggle with complex questions, since their responses "
          + "are based on patterns rather than understanding, they do not excel in complex situations. Thus transperancy about the capabilties should be obvious."
          + "This limitation should be used as a warning to any potential inaccuracies  "
          + "and to avoid relying too heavily on the chatbot for important topics."
        );
        textArea3.setBackground(Color.GRAY);
        textArea3.setForeground(Color.CYAN);
        textArea3.setFont(specialFont1);
        textArea3.setLineWrap(true);
        textArea3.setWrapStyleWord(true);
        textArea3.setEditable(false);
        gbc.gridy = 6;
        informationPanel.add(textArea3, gbc);
        
        JLabel imageLabel4 = new JLabel(new ImageIcon(new ImageIcon("AI4.jpg").getImage().getScaledInstance(400, 150, java.awt.Image.SCALE_SMOOTH))); //creating a jlabel with an image icon that will display an image and add it to the informationPanel
        gbc.gridy = 7;
        informationPanel.add(imageLabel4, gbc);
        
        JTextArea textArea4 = new JTextArea( //after the image,we add this text area, set its font,foreground and background and place it underneath the fourth image 
            "Transparency also plays a key role in preventing the ethical misuse of AI "
          + "chatbots. If not managed right, chatbots can easily learn incorrect data from datasets, thus "
          + "giving users misinformation. Natural Language Processing (NLP) technologies and ethical design  "
          + "makes sure that chatbots are used proerply, which in turn maintains "
          + "honesty in the responses they give."
        );
        textArea4.setBackground(Color.GRAY);
        textArea4.setForeground(Color.CYAN);
        textArea4.setFont(specialFont1);
        textArea4.setLineWrap(true);
        textArea4.setWrapStyleWord(true);
        textArea4.setEditable(false);
        gbc.gridy = 8;
        informationPanel.add(textArea4, gbc);
        
        startQuizButton.addActionListener(e -> {  //Add our action listeners to our buttons each with their respective method
        launchQuizWindow();
        startQuizButton.setEnabled(false);
        });
        
        quitButton.addActionListener(e -> System.exit(0));
        exploreAIButton.addActionListener(e -> {
            openAnimationFrame();
            startQuizButton.setEnabled(true); 
            exploreAIButton.setEnabled(false);
        });
        
        setVisible(true); //setting the frame to be visible
    }

    private void launchQuizWindow() { //method to launch a new frame that will host all process regarding operating the quiz
        
        JFrame quizFrame = new JFrame("Quiz"); //creating a frame called quiz and setting its layout as BorderLayout
        quizFrame.setSize(500, 500);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizFrame.setLayout(new BorderLayout());

        
        JPanel quizPanel = new JPanel(); //add a panel to the quiz and make it boxlayout across the y.axis
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));//set a border and background color 
        quizPanel.setBackground(Color.GRAY);

        
        Font specialFont1 = new Font("Times New Roman", Font.PLAIN, 15); //create a variable called special font that will be used to simplify setting font for all test

        
        JLabel questionLabel = new JLabel(questions[currentQuestionIndex]); //creating a new Jlabel that will hold the question depending on the given index position from the questions list and adding that label onto the quizPanel
        questionLabel.setFont(specialFont1);
        questionLabel.setBackground(Color.BLACK);
        questionLabel.setForeground(Color.CYAN);
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizPanel.add(questionLabel);
        quizPanel.add(Box.createVerticalStrut(15)); //create spacing between the next thing that will be placed

        
        ButtonGroup answerGroup = new ButtonGroup(); //create a button group linking 4 radiobuttons together that will host the choices
        JRadioButton[] answerButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) { //loop through and add each radio button based on the currentQuestionIndex and i, set the font,background and foreground and add them to the answerGroup Buttongroup
            answerButtons[i] = new JRadioButton(choices[currentQuestionIndex][i]);
            answerButtons[i].setFont(new Font("Times New Roman", Font.PLAIN, 16));
            answerButtons[i].setBackground(Color.BLACK);
            answerButtons[i].setForeground(Color.CYAN);
            answerButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            answerGroup.add(answerButtons[i]);
            quizPanel.add(answerButtons[i]);
        }
        quizPanel.add(Box.createVerticalStrut(15));// add spacing to the next place

        
        JLabel feedbackLabel = new JLabel(""); //add the empty feedback label which will be updated later and set font,foreground and background
        feedbackLabel.setFont(specialFont1);
        feedbackLabel.setBackground(Color.BLACK);
        feedbackLabel.setForeground(Color.CYAN);
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizPanel.add(feedbackLabel);
        quizPanel.add(Box.createVerticalStrut(15)); //add spacing for next widget

        
        JLabel scoreLabel = new JLabel("Score: 0"); //scoreLabel which will hold the current score of the user, and setting its font,background and foreground.
        scoreLabel.setFont(specialFont1);
        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setForeground(Color.CYAN);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizPanel.add(scoreLabel);
        quizPanel.add(Box.createVerticalStrut(15));

        
        JProgressBar progressBar = new JProgressBar(0, questionsLength); //add a progress bar that will increment with the currentQuestion index
        progressBar.setValue(1); 
        progressBar.setStringPainted(true);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizPanel.add(progressBar);
        quizPanel.add(Box.createVerticalStrut(15));

        
        JPanel navigationPanel = new JPanel(new FlowLayout()); //add a new panel which will hold three buttons, next, previous and check answer. We set the font,foreground and background for these buttons
        JButton previousButton = new JButton("Previous Question");
        previousButton.setFont(specialFont1);
        previousButton.setBackground(Color.BLACK);
        previousButton.setForeground(Color.CYAN);

        JButton validateAnswerButton = new JButton("Check Answer");
        validateAnswerButton.setFont(specialFont1);
        validateAnswerButton.setBackground(Color.BLACK);
        validateAnswerButton.setForeground(Color.CYAN);


        JButton nextButton = new JButton("Next Question");
        nextButton.setFont(specialFont1);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.CYAN);

        navigationPanel.add(previousButton);
        navigationPanel.add(validateAnswerButton);
        navigationPanel.add(nextButton);
        navigationPanel.setBackground(Color.GRAY); //adding the buttons to the navigationPanel and setting the background as gray

        quizPanel.add(navigationPanel, BorderLayout.SOUTH);// add the navigation panel to the quiz panel

       
        quizFrame.add(quizPanel, BorderLayout.CENTER); //add the quizpanel to the quiz frame
        quizFrame.setVisible(true);//set the whole frame to be visible

        
        nextButton.addActionListener(e -> { //add actionlistener for the next button which will refresh the feedback label, set the index i for the selected choice back to no selection and then call the method goToNextQuestion
            
            feedbackLabel.setText("");
            for (int i = 0; i < 4; i++) {
                answerButtons[i].setSelected(false);
            }
            goToNextQuestion(questionLabel, answerButtons, progressBar, feedbackLabel, scoreLabel, quizFrame);
        });

        
        previousButton.addActionListener(e -> { //button will call method goToPreviousQuestion and go to the previous question
            goToPreviousQuestion(questionLabel, answerButtons, progressBar, feedbackLabel, scoreLabel);
        });

        
        validateAnswerButton.addActionListener(e -> { //button will call the method to validate the answer
            validateAnswer(answerGroup, feedbackLabel, answerButtons, scoreLabel);
        });
    }

    
    private void displayQuestion(JLabel questionLabel, JRadioButton[] answerButtons, //method displayquestion that is used in goToNextQuestion and goToPreviousQuestion. Set as Private as good coding practice if this was across multiple classes
                                 JLabel scoreLabel, JLabel feedbackLabel) {
        
        questionLabel.setText(questions[currentQuestionIndex]);//set the questionLabelText to the index position of whatever value currentQuestionIndex is

        
        answerButtons[0].setText(choices[currentQuestionIndex][0]); //set the text of the answerButtons to the respective value of currentQuestionindex
        answerButtons[1].setText(choices[currentQuestionIndex][1]);
        answerButtons[2].setText(choices[currentQuestionIndex][2]);
        answerButtons[3].setText(choices[currentQuestionIndex][3]);

        
        if (answeredQuestions[currentQuestionIndex]) { //if the value of the currentQuestionindex within the boolean answeredQuestions is true, then disable the buttons, if not then keep them enabled
            answerButtons[0].setEnabled(false);
            answerButtons[1].setEnabled(false);
            answerButtons[2].setEnabled(false);
            answerButtons[3].setEnabled(false);
        } else {
            
            answerButtons[0].setEnabled(true);
            answerButtons[0].setSelected(false);
            answerButtons[1].setEnabled(true);
            answerButtons[1].setSelected(false);
            answerButtons[2].setEnabled(true);
            answerButtons[2].setSelected(false);
            answerButtons[3].setEnabled(true);
            answerButtons[3].setSelected(false);

            
            feedbackLabel.setText(""); //reset the feedback to blank
        }

        
        scoreLabel.setText("Score: " + score); //set the current score value and update it
    }

   
    private void validateAnswer(ButtonGroup answerGroup, JLabel feedbackLabel, //method to check answer, set as Private as good coding practice if this was across multiple classes 
                                JRadioButton[] answerButtons, JLabel scoreLabel) {
        String correctAnswer = answers[currentQuestionIndex]; //get the correct answer from the answer list and the corresponding currentQuestionIndex

        
        String selectedAnswer = null; //if nothing is selected then it is null
        if (answerButtons[0].isSelected()) { //this just retrieves and sets a variable called selected answer for whichever choice is selected
            selectedAnswer = answerButtons[0].getText();
        } else if (answerButtons[1].isSelected()) {
            selectedAnswer = answerButtons[1].getText();
        } else if (answerButtons[2].isSelected()) {
            selectedAnswer = answerButtons[2].getText();
        } else if (answerButtons[3].isSelected()) {
            selectedAnswer = answerButtons[3].getText();
        }

        if (selectedAnswer == null) { //if nothing is chosen the feedback label will be set to please select answer
            feedbackLabel.setText("Please select an answer.");
            feedbackLabel.setForeground(Color.RED);
        } else {
            if (selectedAnswer.equals(correctAnswer)) { //if the question equals the correct answer, display correct and increment the score by 1
                score++;
                feedbackLabel.setText("Correct!");
                feedbackLabel.setForeground(Color.GREEN);
            } else { //if it is not correct then tell the user that they got it wrong and tell them the correct answer
                feedbackLabel.setText("Incorrect! The correct answer is: " + correctAnswer);
                feedbackLabel.setForeground(Color.RED);
            }
            answeredQuestions[currentQuestionIndex] = true; //for whatever index the currentQuestionIndex represents, it will set the value to true so that in the display function, when it checks whether to disable the choices or not, it can disable them.

            
            if (answerButtons[0].isEnabled()) { //making sure that all the buttons are no longer selected after the answer has been given
                answerButtons[0].setEnabled(false);
            }
            if (answerButtons[1].isEnabled()) {
                answerButtons[1].setEnabled(false);
            }
            if (answerButtons[2].isEnabled()) {
                answerButtons[2].setEnabled(false);
            }
            if (answerButtons[3].isEnabled()) {
                answerButtons[3].setEnabled(false);
            }

            
            scoreLabel.setText("Score: " + score); //update the score
        }
    }

    private void goToNextQuestion(JLabel questionLabel, JRadioButton[] answerButtons, //method to move on to the next question, set as Private as good coding practice if this was across multiple classes
                                  JProgressBar progressBar, JLabel feedbackLabel,
                                  JLabel scoreLabel, JFrame quizFrame) {
        if (currentQuestionIndex < questionsLength - 1) { 
            currentQuestionIndex++; //increment the currentQuestionIndex so that everything else aligns in this code
            
            progressBar.setValue(currentQuestionIndex + 1); //update the progressbar value to increase by 1

           
            displayQuestion(questionLabel, answerButtons, scoreLabel, feedbackLabel); //call the displayQuestionFunction which will show the question
        } else {
            
            JOptionPane.showMessageDialog(quizFrame, //if the currentQuestionindex is greather than the length of questions then it knows that the quiz is over and tell them the final score and quit the program
                "Quiz completed!\nFinal Score: " + score);
            System.exit(0);
        }
    }

    private void goToPreviousQuestion(JLabel questionLabel, JRadioButton[] answerButtons, //method to go back to the previous question, set as Private as good coding practice if this was across multiple classes
                                      JProgressBar progressBar, JLabel feedbackLabel,
                                      JLabel scoreLabel) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--; //decreases the currentQuestionIndex variable by a value of 1
            progressBar.setValue(currentQuestionIndex + 1); //updates the progressbar accordingly which decreases it

            displayQuestion(questionLabel, answerButtons, scoreLabel, feedbackLabel); //call the displayQuestion method so that the previous question can be displayed
        } else {
            JOptionPane.showMessageDialog(this, "Sorry, not possible"); //if no more questions to go back to, will show a messagedialog that will tell the user that there is nothing else there
        }
    }

   
    class AnimationApp extends JPanel { //class that will hold all relavant information to animation portion of the app, is class due to taking code from original animation app assignmen
        Image[] leftFrames; //intiliasing lists which will hold paths for character animation and backgrounds
        Image[] rightFrames;
        Image[] downFrames;
        Image[] currentFrames;
        Image[] backgrounds;
        int[] initialYPositions; //setting a list for the intialypositions to cycle with each background so that character matches with background
        int currentImageIndex = 0; //setting variable for currentImageindex
        int currentBackgroundIndex = 0; //setting variable for curretnbackgroundindex
        
        int x = 365; //setting intitial positions
        int dx = 0;
        int y = 0;

        public AnimationApp() {
            leftFrames = new Image[] { //inputting all my paths to my pictures that will be used in the animation
                new ImageIcon("VL1.png").getImage(),
                new ImageIcon("VL2.png").getImage(),
                new ImageIcon("VL3.png").getImage(),
                new ImageIcon("VL4.png").getImage(),
                new ImageIcon("VL5.png").getImage(),
                new ImageIcon("VL6.png").getImage(),
                new ImageIcon("VL7.png").getImage(),
                new ImageIcon("VL8.png").getImage(),
                new ImageIcon("VL9.png").getImage()
            };
            rightFrames = new Image[] {
                new ImageIcon("VR1.png").getImage(),
                new ImageIcon("VR2.png").getImage(),
                new ImageIcon("VR3.png").getImage(),
                new ImageIcon("VR4.png").getImage(),
                new ImageIcon("VR5.png").getImage(),
                new ImageIcon("VR6.png").getImage(),
                new ImageIcon("VR7.png").getImage(),
                new ImageIcon("VR8.png").getImage(),
                new ImageIcon("VR9.png").getImage()
            };
            downFrames = new Image[] {
                new ImageIcon("VF1.png").getImage()
            };
            currentFrames = downFrames;
            backgrounds= new Image[] {
            new ImageIcon("background.jpg").getImage(),
            new ImageIcon("background2.jpg").getImage(),
            new ImageIcon("background3.jpg").getImage()
            };
            initialYPositions = new int[] { 265, 225, 200 }; //setting postions that are corresponding
            y = initialYPositions[0];

            Timer timer = new Timer(100, new ActionListener() {  //setting a timer to handle the animation and movement, updating every 100ms
                public void actionPerformed(ActionEvent e) {
                    currentImageIndex = (currentImageIndex + 1) % currentFrames.length; //cycle through the animation frames
                    x += dx; //update the positon of x based on the key pressed
                    
                     if (x >= getWidth() - 50) { //if the character reaches right edge, reset the x value and increment the currentBackground index to change the background
                        x = 0; 
                        currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
                        y = initialYPositions[currentBackgroundIndex];
                     } 
                     else if (x <= 0) { //if the character reaches left edge, reset the x value and adjust background accordingly
                        x = getWidth() - 50; 
                        currentBackgroundIndex = (currentBackgroundIndex - 1 + backgrounds.length) % backgrounds.length;
                        y = initialYPositions[currentBackgroundIndex];
                     }   
                    
                    repaint();
                }
            });
            timer.start(); //starting the animation

            setFocusable(true); 
            addKeyListener(new KeyAdapter() { //setting the listeeners for the keys which will switch the animation to either left or right
                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_LEFT) {
                        dx = -5;
                        currentFrames = leftFrames;
                    } else if (key == KeyEvent.VK_RIGHT) {
                        dx = 5;
                        currentFrames = rightFrames;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    dx = 0; //when no key is pressed no movement and reset the character to a standard position
                    currentFrames = downFrames;
                    currentImageIndex = 0;
                    repaint();
                }
            });
        }

        @Override //paint component to draw background and animation frames
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgrounds[currentBackgroundIndex], 0, 0, getWidth(), getHeight(), null);
            if (currentFrames[currentImageIndex] != null) {
                 g.drawImage(currentFrames[currentImageIndex], x, y, null);
            }
        }
    }

    private void openAnimationFrame() { //this method opens the Jframe that holds the animation by calling the class
        JFrame frame = new JFrame("Directional Animation");
        AnimationApp animation = new AnimationApp();
        frame.add(animation);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        frame.addWindowListener(new WindowAdapter() { //add a windowlistener that has the responsibility to close the audio if the page is no longer open
            @Override
            public void windowClosing(WindowEvent e) {
                if (mediaPlayer != null) { //if mediaplayer does not equal null then it will stop the music
                    mediaPlayer.stop();
                    mediaPlayer = null; // ensure next re-open starts the music from the beginning
                }
            }
        });

        frame.setVisible(true);

        if (mediaPlayer == null) {
            Media media = new Media(new File("music.mp3").toURI().toString()); //since java only accepts .wav files, and I have a mp3, i need to convert mp3 to wav so that it can be reconized
            mediaPlayer = new MediaPlayer(media); //assign a mediaplayer to the actual media that was from before
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //make sure it continues indefinetly
            mediaPlayer.play(); //start the music
        } 
    }

    public static void main(String[] args) {
        new AI(); //calling AI to run the whole code.
    }
}




