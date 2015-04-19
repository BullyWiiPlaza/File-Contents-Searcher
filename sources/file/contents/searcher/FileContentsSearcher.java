package file.contents.searcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import utilities.jar.JarName;

public class FileContentsSearcher
{
	private String targetDirectory;
	private String searchTerm;
	private List<File> allFiles;

	public FileContentsSearcher(String targetDirectory, String searchTerm)
	{
		this.targetDirectory = targetDirectory;
		this.searchTerm = searchTerm;
	}

	public static void main(String[] arguments) throws IOException
	{
		if (arguments.length != 2)
		{
			System.err
					.println("Scans all files in [targetDirectory] and prints the absolute paths of files that contain [searchTerm]."
							+ System.lineSeparator()
							+ "Usage: java -jar "
							+ new JarName().get()
							+ " [targetDirectory] [searchTerm]");
		} else
		{
			FileContentsSearcher fileContentsSearcher = new FileContentsSearcher(
					arguments[0], arguments[1]);

			fileContentsSearcher.readAllFiles();
			fileContentsSearcher.processFiles();
		}
	}

	public void readAllFiles()
	{
		allFiles = (List<File>) listFiles(targetDirectory);
	}

	private void appendToFile(String filePath, String text)
	{
		PrintWriter fileWriter = null;

		try
		{
			fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					filePath, true)));

			fileWriter.println(text);
		} catch (IOException ioException)
		{
			ioException.printStackTrace();
		} finally
		{
			if (fileWriter != null)
			{
				fileWriter.close();
			}
		}
	}

	public void processFiles() throws IOException
	{
		String fileName = "output.txt";
		new File(fileName).delete();

		for (File currentFile : allFiles)
		{
			String currentFileContents = FileUtils
					.readFileToString(currentFile);

			if (currentFileContents.contains(searchTerm))
			{
				String currentFilePath = currentFile.getAbsolutePath();

				System.out.println(currentFilePath);
				appendToFile("output.txt", currentFilePath);
			}
		}
	}

	private Collection<File> listFiles(String backupFolder)
	{
		return FileUtils.listFiles(new File(backupFolder),
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
	}
}