package constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum FilePath {;
	
	public static final Path path = Paths.get("C:\\Users\\stefa\\eclipse-workspace\\MyPlaces\\");
	public static final Path alb_path = path.resolve("data\\location\\alberghi");
	public static final Path admin_path = path.resolve("data\\amministratori");
	public static final Path appart_path = path.resolve("data\\location\\appartamenti");
	
}
