import java.nio.*;
import java.io.*;
import java.net.*;

import java.nio.channels.*;

public class wget {

	public static void main(String args[]) {
	try {
		if (args.length < 2) {
			System.err.println("~~~~ Error! Not enough argments specified!");
			System.err.println("~~~~ usage: java wget [url] [filename] [(optional) pathname]");
			System.exit(1);
		}
		if (args[0] != null && args.length == 2) {
			System.out.println("~~~~ Downloading...");
			URL website = new URL(args[0]);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());

			if (args[1].indexOf(File.separator) != -1) { System.err.println("~~~~ Error! Please do not place slashes in the filename!"); System.exit(1); }
			
			FileOutputStream fos = new FileOutputStream(args[1]);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			System.out.println("~~~~ Done! No errors to report!");
			System.exit(0);
		}
		if (args[0] != null && args.length > 1) {
			System.out.println("~~~~ Downloading...");
			URL website = new URL(args[0]);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			
			if (args[1].indexOf(File.separator) != -1) { System.err.println("~~~~ Error! Please do not place slashes in the filename!"); System.exit(1); }
			
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + File.separator + args[2] + File.separator + args[1]);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			System.out.println("~~~~ Done! No errors to report!");
			System.exit(0);
		}
	} catch (Exception e) { System.err.println(e.toString()); }
	}
}