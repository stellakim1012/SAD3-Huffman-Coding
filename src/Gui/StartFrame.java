package Gui;

import javax.swing.*;
import javax.swing.filechooser.*;

import File.FileRead;
import Huff.Decode;
import Huff.Encode;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class StartFrame
{
	private JFrame frame;
	JLabel TitleHuff=new JLabel("Huffman Coding");
	JButton fileOpen=new JButton("File Open");
	JButton EncodeButton=new JButton("압축");
	JButton DecodeButton=new JButton("압축해제");
	JPanel panel1=new JPanel();
	JPanel panelN=new JPanel();
	JPanel panelS=new JPanel();
	JPanel panelW=new JPanel();
	JPanel panelE=new JPanel();
	String filePath;
	String fileName;
	Container c;
	JTextField textField;
	FileRead file;
	
	
	public StartFrame()
	{
		frame=new JFrame();
//		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		c=frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Huffman Coding");
		panel1.setLayout(null);
		TitleHuff.setForeground(new Color(0, 0, 0));
		
		TitleHuff.setHorizontalAlignment(SwingConstants.CENTER);
		TitleHuff.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		TitleHuff.setBounds(112, 10, 240, 76);
		
		panel1.add(TitleHuff);
		fileOpen.setBackground(SystemColor.menu);
		fileOpen.setFont(new Font("나눔고딕", Font.BOLD, 12));
		fileOpen.setBounds(185, 80, 87, 23);
		fileOpen.addActionListener(new SwingAction());
		fileOpen.addActionListener(new OpenActionListener());
		panel1.add(fileOpen);
		EncodeButton.setBackground(SystemColor.menu);
		EncodeButton.setFont(new Font("한컴 고딕", Font.BOLD, 16));
		
		EncodeButton.setBounds(124, 140, 100, 30);
		EncodeButton.addActionListener(new SwingAction());
		panel1.add(EncodeButton);
		DecodeButton.setBackground(SystemColor.menu);
		DecodeButton.setFont(new Font("한컴 고딕", Font.BOLD, 16));
		
		DecodeButton.setBounds(236, 140, 100, 30);
		DecodeButton.addActionListener(new SwingAction());
		panel1.add(DecodeButton);
		
		EncodeButton.setEnabled(false);
		DecodeButton.setEnabled(false);
		
//		panel1.setBackground(new Color(125, 125, 255));
		panel1.setBackground(new Color(255, 255, 255));
		panelE.setBackground(SystemColor.menu);
		panelN.setBackground(SystemColor.menu);
		panelS.setBackground(SystemColor.menu);
		panelW.setBackground(SystemColor.menu);
		
		c.add(panel1,BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(124, 113, 212, 21);
		panel1.add(textField);
		textField.setColumns(10);
		c.add(panelE,BorderLayout.EAST);
		c.add(panelN,BorderLayout.NORTH);
		c.add(panelS,BorderLayout.SOUTH);
		c.add(panelW,BorderLayout.WEST);
		
		frame.setSize(500,250);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	class OpenActionListener implements ActionListener
	{
		JFileChooser chooser;
		
		OpenActionListener()
		{
			chooser=new JFileChooser();
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			FileNameExtensionFilter filter=new FileNameExtensionFilter("txt & zip & unzip", //파일 이름 창에 출력될 문자열
					"txt","txt(HuffZip)","txt(HuffZip)(unzip)"); //파일 다이얼로그에 파일 필터 설정
			chooser.setFileFilter(filter); //파일 다이얼로그에 파일 필터 설정
			
			//파일 다이얼로그 출력
			int ret=chooser.showOpenDialog(null);
			if(ret!=JFileChooser.APPROVE_OPTION)
			{
				//사용자가 창을 강제롤 닫았거나 취소 버튼을 누른 경우
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다");
				return;
			}
			//사용자가 파일을 선택하고 "열기"버튼을 누른 경우
			filePath=chooser.getSelectedFile().getPath(); //파일 경로를 가져온다.
			fileName=chooser.getSelectedFile().getName(); //파일 이름을 가져온다.
			
			
			if(fileName.contains("(HuffZip)") && !fileName.contains("(unzip)"))
				DecodeButton.setEnabled(true);
			else
				EncodeButton.setEnabled(true);
			
			textField.setText(fileName);
			try {
				file=new FileRead(filePath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private class SwingAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand()=="압축해제")
			{
				new Decode(file, filePath);
				frame.setVisible(false);
			}
			else if(e.getActionCommand()=="압축")
			{
				new Encode(file,filePath);
				frame.setVisible(false);
			}
			else if(e.getActionCommand()=="File Open")
			{
				new OpenActionListener();
			}
		}
	}
}