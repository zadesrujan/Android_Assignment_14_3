package com.example.user.android_assignment_14_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    EditText editText;
    TextView textView;
    //Intializing the text and view.
    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design

        editText= (EditText) findViewById(R.id.edittext);
        //giving id to the text
        textView= (TextView) findViewById(R.id.textview);
        //giving id to the text view
        textView.setVisibility(View.GONE);
        //setting the visibility as gone of text view.
    }
    //Method to write messege in file
    public void writeMessage(View view){
        String message= editText.getText().toString();
        String file_Name="file";
        //giving file name as string as file and assigning to the edit text.
        try {

            FileOutputStream fileOutputStream = openFileOutput(file_Name,MODE_PRIVATE);
            //A file output stream is an output stream for writing data to a File or to a FileDescriptor.
            fileOutputStream.write(message.getBytes());
            //for writing the data
            fileOutputStream.close();
            //closing
            Toast.makeText(getApplicationContext(),"message saved sucessfully",Toast.LENGTH_LONG).show();
            //after writing the data clicking on the save button shows the toast as message saved sucessfully
            editText.setText("");
            //giving edit text as empty string
        } catch (FileNotFoundException e) {
           // This exception will be thrown by the FileInputStream, FileOutputStream, and RandomAccessFile constructors.
            // when a file with the specified pathname does not exist.
            e.printStackTrace();
        } catch (IOException e) {
            //Signals that an I/O exception of some sort has occurred.
            // This class is the general class of exceptions produced by failed or interrupted I/O operations.
            e.printStackTrace();
        }

    }
    //Read file in Internal Storage we use this method
    public void readMessage(View view){
        try {
            String Message ;
            FileInputStream fileInputStream=openFileInput("file");
            //A file input stream is an input stream for reading data to a File or to a FileDescriptor.
            InputStreamReader inputStreamReader= new InputStreamReader(fileInputStream);
            //for reading the data we use input stream reader
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            //It is used to create a buffered character input stream that uses the specified size for an input buffer.
            StringBuffer buffer= new StringBuffer();
            //creating new object as buffer and adding to the string
            textView.setText(buffer.toString());
            //setting the string to the text view

            try {
                while ((Message = bufferedReader.readLine())!=null) {
                    //When receiving data using readLine(), even though I put a "\n" at the end of the message using the .
                    // flush when sending the message, the while loop that reads my message still blocks.
                    // Only when closing the socket connection, it leaves the loop
                    buffer.append(Message + "\n");
                    Log.d("Message",buffer.toString());

                }
                textView.setText(buffer.toString());
                //connecting to the buffer and to string
                textView.setVisibility(View.VISIBLE);
                //setting as text view as visible by giving the visible option
            } catch (IOException e) {
                //Signals that an I/O exception of some sort has occurred.
                // This class is the general class of exceptions produced by failed or interrupted I/O operations.
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //This exception will be thrown by the FileInputStream, FileOutputStream, and RandomAccessFile constructors.
            // when a file with the specified pathname does not exist.
            e.printStackTrace();
        }

    }
}

