import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame{
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String serverIP;
    private Socket connection;


    // constructor
    public Client(String host){
  		super("Client");
  		serverIP = host;
  		userText = new JTextField();
  		userText.setEditable(false);
  		userText.addActionListener(
  				new ActionListener(){
  				public void actionPerformed(ActionEvent event){
  					sendMessage(event.getActionCommand());
  					userText.setText("");
  				}
  			}
  		);
  		add(userText, BorderLayout.NORTH);
  		chatWindow = new JTextArea();
  		add(new JScrollPane(chatWindow));
  		setSize(300, 150); //Sets the window size
  		setVisible(true);
  	}

    // connect to server
    public void startRunning(){
      try{
        connectToServer();
        setupStreams();
        whileChatting();

      }catch(EOFException eofException){
        showMessage("\nClient terminated connection");
      }catch(IOException ioException){
        ioException.printStackTrace();
      }finally{
        closeCrap();
      }
    }
    private void connectToServer() throws IOException{
      showMessage("Attempting connection...\n");
      connection = new Socket(InetAddress.getByName(serverIP),6789);
      showMessage("Connected to "+connection.getInetAddress().getHostName());
    }
    // setup streams to send/receive messages
    private void setupStreams() throws IOException{
      output = new ObjectOutputStream(connection.getOutputStream());
      output.flush();
      input = new ObjectInputStream(connection.getInputStream());
      showMessage("\n Streams initialized \n");
    }
    // while chatting with server
    private void whileChatting() throws IOException{
      ableToType(true);
      do {
        try{
          message  = (String) input.readObject();
          showMessage("\n "+message);
        }catch(ClassNotFoundException classNotFoundException){
          showMessage("\n Unknown object type");
        }
      } while (!message.equals("SERVER - END"));
    }
    // close the streams and sockets
    private void closeCrap(){
      showMessage("\n Exiting...");
      ableToType(false);
      try
      {
        output.close();
        input.close();
        connection.close();
      }catch(IOException ioException){
        ioException.printStackTrace();
      }
    }
    // send messages to sever
    private void sendMessage(String message){
      try {
        output.writeObject("CLIENT - "+message);
        output.flush();
        showMessage("\n CLIENT - "+message);
      }catch(IOException ioException){
        chatWindow.append("\n Could not send message.");
      }
    }
    // change/update chatWindow
    private void showMessage(final String m){
      SwingUtilities.invokeLater(
        new Runnable(){
          public void run(){
            chatWindow.append(m);
          }
        }
      );
    }

    // gives user permission to type into the textbox
    private void ableToType(final boolean tof){
      SwingUtilities.invokeLater(
        new Runnable(){
          public void run(){
            userText.setEditable(tof);
          }
        }
      );
    }
}
