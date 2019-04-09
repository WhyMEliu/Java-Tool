package com.peng.monitor_tuning.btrace;
import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class PrintArgSimple {
	
	@OnMethod(
			//方法类
	        clazz="com.peng.monitor_tuning.btrace.BtraceController",
	        //方法名
	        method="arg1",
	        //监控点
	        location=@Location(Kind.ENTRY)
	)
	//类名：@ProbeClassName，方法名：@ProbeMethodName，参数：AnyType
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
		BTraceUtils.printArray(args);
		BTraceUtils.println(pcn+","+pmn);
		BTraceUtils.println();
    }
}
