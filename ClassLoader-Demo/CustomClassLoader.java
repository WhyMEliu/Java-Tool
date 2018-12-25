package classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * <b>修改记录：</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018年12月25日
 * </li>
 * </p>
 * 
 * <b>类说明：</b>
 * <p> 
 * 
 * </p>
 */
public class CustomClassLoader extends ClassLoader {
	//需要加载的class文件
	private final String classDir;
	
	public CustomClassLoader(String classDir){
		this.classDir = classDir;
	}
	
	//寻找需要加载的class文件
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileName = name;
		if(fileName.indexOf('.') !=-1){
			fileName = fileName.replaceAll("\\.", "\\\\");
		}
		fileName = fileName + ".class";
		try {
			//将.class文件输出为流 转为byte 传入defineClass 本地方法 转为 java.lang.Class对象
			try(FileInputStream in = new FileInputStream(classDir + fileName)){
				try(ByteArrayOutputStream out = new ByteArrayOutputStream()){
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len = in.read(buffer)) != -1){
						out.write(buffer, 0, len);
					}
					byte[] data =out.toByteArray();
					return defineClass(name, data, 0, data.length);
				}
			}
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}
	}
	
	
	
	public static void main(String[] args) throws ReflectiveOperationException {
		
		ClassLoader classLoader = new CustomClassLoader("F:\\classes\\");
		Class<?> clazz = classLoader.loadClass("ClassLoaderTest");
		//利用反射进行方法调用~
		Object intstance = clazz.newInstance();
		Method method = clazz.getMethod("test", null);
		method.invoke(intstance);
	}
	
}
