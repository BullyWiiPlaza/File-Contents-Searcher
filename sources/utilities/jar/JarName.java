package utilities.jar;

import java.io.File;

public class JarName
{
	public String get()
	{
		return new File(this.getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath()).getName();
	}
}