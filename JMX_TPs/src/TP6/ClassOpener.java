package TP6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class ClassOpener {
	File file;

	public ClassOpener(){
		JFileChooser fileChooser=new  JFileChooser();
		fileChooser.setVisible(true);
		fileChooser.setCurrentDirectory(new File("E:\\EclipseWorkSpace\\JMX_TPs\\TP6\\"));
		if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			file=fileChooser.getSelectedFile();
		}
	}

	public File getFile() {
	    return file;
	}
}