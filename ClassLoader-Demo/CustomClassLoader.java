package classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * <b>�޸ļ�¼��</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018��12��25��
 * </li>
 * </p>
 * 
 * <b>��˵����</b>
 * <p> 
 * 
 * </p>
 */
public class CustomClassLoader extends ClassLoader {
	//��Ҫ���ص�class�ļ�
	private final String classDir;
	
	public CustomClassLoader(String classDir){
		this.classDir = classDir;
	}
	
	//Ѱ����Ҫ���ص�class�ļ�
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileName = name;
		if(fileName.indexOf('.') !=-1){
			fileName = fileName.replaceAll("\\.", "\\\\");
		}
		fileName = fileName + ".class";
		try {
			//��.class�ļ����Ϊ�� תΪbyte ����defineClass ���ط��� תΪ java.lang.Class����
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
		//���÷�����з�������~
		Object intstance = clazz.newInstance();
		Method method = clazz.getMethod("test", null);
		method.invoke(intstance);
	}
	
}
