package com.peng.monitor_tuning.btrace;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class PrintSame {
	
	@OnMethod(
	        clazz="com.peng.monitor_tuning.btrace.Ch4Controller",
	        method="same"
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, String name) {
		BTraceUtils.println(pcn+","+pmn + "," + name);
		BTraceUtils.println();
    }
}
