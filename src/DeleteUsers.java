import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeleteUsers extends JPanel{
	private JLabel jnumber,jname,jpassword,jcollege,jsubject,jgrade;
	private JTextField number,name,college,subject,grade;
	private JPasswordField password;
	private JButton button1,button2;
	PrintStream output;
	DataInputStream input;
	String message = "";
	String bookinfor[];

	public DeleteUsers(){
		this.setLayout(null);
		jnumber = new JLabel("Student ID");
		number = new JTextField();
		jname = new JLabel("Name");
		name = new JTextField();
		jpassword = new JLabel("Password");
		password = new JPasswordField();
		jcollege = new JLabel("College");
		college = new JTextField();
		jsubject = new JLabel("Subject");
		subject = new JTextField();
		jgrade = new JLabel("Grade");
		grade = new JTextField();
		button1 = new JButton("Check");
		button2 = new JButton("Delete");
		
		//set Controller's Bounds
		jnumber.setBounds(30,20,60,26);
		jname.setBounds(30,100,60,26);
		jpassword.setBounds(30,130,60,26);
		jcollege.setBounds(30,160,60,26);
		jsubject.setBounds(30,190,60,26);
		jgrade.setBounds(30,220,60,26);	
		number.setBounds(110,20,120,26);
		name.setBounds(110,100,120,26);
		password.setBounds(110,130,120,26);
		college.setBounds(110,160,120,26);
		subject.setBounds(110,190,120,26);
		grade.setBounds(110,220,120,26);		
		button1.setBounds(60,60,60,30);
		button2.setBounds(60,250,60,26);
		
		//set JTextField Not Editable
		name.setEnabled(false);
		password.setEnabled(false);
		college.setEnabled(false);
		subject.setEnabled(false);
		grade.setEnabled(false);
		
		//add Modules
		this.add(jnumber);
		this.add(number);
		this.add(button1);
		this.add(jname);
		this.add(name);
		this.add(jpassword);
		this.add(password);
		this.add(jcollege);
		this.add(college);
		this.add(jsubject);
		this.add(subject);
		this.add(jgrade);
		this.add(grade);
		this.add(button2);
		
		//add ActionListener for button1
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					if(number.getText().toString().equals(""))
						JOptionPane.showMessageDialog(null, "Student ID cannot be Empty!", "Delete", JOptionPane.WARNING_MESSAGE);
					String sqlstr1;
					JdbcFiles conn = new JdbcFiles();
					sqlstr1 = "select number,name,password,college,subject,grade from users where convert(nvarchar(255),number)='"+number.getText()+"'";
					ResultSet result=conn.executeQuery(sqlstr1);							
					   if(result.next()){
						   {
							   name.setText(result.getString("name"));
							   password.setText(result.getString("password"));
							   college.setText(result.getString("college"));
							   subject.setText(result.getString("subject"));
							   grade.setText(result.getString("grade"));
						   }
					}else
						JOptionPane.showMessageDialog(null, "Student Information Not Found.", "Delete",JOptionPane.WARNING_MESSAGE);
					   }catch (ClassNotFoundException ce) {
						   System.out.println("SQLException:" + ce.getMessage());
						   }catch (SQLException ex) {
							   System.out.println(ex);
							   } catch (Exception s) {
								   s.printStackTrace();
				}

				}
			
			
		});
		
		//add ActionListener for button2
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
	try{
					
					String sqlstr2="delete from users where convert(nvarchar(255),number)='"+number.getText()+"'";
					int k=-1;
					JdbcFiles conn1=new JdbcFiles();
					k=conn1.insert(sqlstr2);
					
					if(number.getText().toString().equals(""))
						JOptionPane.showMessageDialog(null, "Student ID cannot be Empty!","Edit",JOptionPane.WARNING_MESSAGE);	
					else if(name.getText().toString().equals(""))
						JOptionPane.showMessageDialog(null, "Name cannot be Empty!","Edit",JOptionPane.WARNING_MESSAGE);	
					else if(password.getText().toString().equals(""))
						JOptionPane.showMessageDialog(null, "Password cannot be Empty!","Edit",JOptionPane.WARNING_MESSAGE);	
					else if(college.getText().toString().equals(""))
						JOptionPane.showMessageDialog(null, "College cannot be Empty!","Edit",JOptionPane.WARNING_MESSAGE);	
					else if(subject.getText().toString().equals(""))
						JOptionPane.showMessageDialog(null, "Subject cannot be Empty!","Edit",JOptionPane.WARNING_MESSAGE);	
					else if(grade.getText().toString().equals(""))
						JOptionPane.showMessageDialog(null, "Grade cannot be Empty!","Edit",JOptionPane.WARNING_MESSAGE);
					else if (k > -1){
						JOptionPane.showMessageDialog(null, "Delete Successful!", "Delete",JOptionPane.WARNING_MESSAGE);
						conn1.close();
						} else
							JOptionPane.showMessageDialog(null, "Delete Failed!", "Delete",JOptionPane.WARNING_MESSAGE);									
					}catch(ClassNotFoundException ce){
						System.out.println("SQLException:"+ce.getMessage());
						
					}catch(SQLException ex){
						System.out.println(ex);
						
					}catch(Exception s){
						s.printStackTrace();
						}
				}
		});
		setSize(250,380);
		this.setVisible(true);
	}
		
	public static void main(String[] args){
		
		DeleteUsers app1=new DeleteUsers();
			
	}	
}



