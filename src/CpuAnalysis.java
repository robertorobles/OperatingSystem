import java.io.File;
import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class CpuAnalysis {
	
	private static int MB = 1024*1024;
	private static int GB = 1024*1024*1024;

	private OperatingSystemMXBean operatingSystemMXBean;

	public CpuAnalysis() {
		operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	}

	public void printCpuUsage() {
		double cpuLoad = operatingSystemMXBean.getSystemCpuLoad();
		
		System.out.print("\n\n------CPU------");
		System.out.printf("\nCPU load: %.0f%%", cpuLoad * 100.0);
		
		
	}

	public void printMemoryUsage() {
		long total = operatingSystemMXBean.getTotalPhysicalMemorySize();
		long free = operatingSystemMXBean.getFreePhysicalMemorySize();
		long used = total - free;
		double percentUsed = ((double) used / (double) total)* 100.0;
		double percentFree = ((double) free / (double) total)* 100.0;

		System.out.print("\n\n------MEMORY------");
		System.out.printf("\nTotal Memory: %dMB", total/MB);
		System.out.printf("\nUsed Memory: %dMB (%.0f%%)", used/MB, percentUsed);
		System.out.printf("\nFree Memory: %dMB (%.0f%%)", free/MB, percentFree);
	}

	public void printDiskUsage() {
		System.out.print("\n\n------DISK------");
		File[] roots = File.listRoots();
		for (File root : roots) {
			String path = root.getAbsolutePath();
			long total = root.getTotalSpace();
			long free = root.getFreeSpace();
			long used = total - free;
			double percentUsed = ((double) used / (double) total)* 100.0;
			double percentFree = ((double) free / (double) total)* 100.0;
			
			System.out.printf("\nFileSystem Root Details: %s", path);
			System.out.printf("\nTotal Space: %dGB", total/GB);
			System.out.printf("\nUsed Space: %dGB (%.0f%%)", used/GB, percentUsed);
			System.out.printf("\nFree Space: %dGB (%.0f%%)", free/GB, percentFree);
		}
	}

	public static void main(String[] args) {
		CpuAnalysis cpuAnalysis = new CpuAnalysis();
		cpuAnalysis.printCpuUsage();
		cpuAnalysis.printMemoryUsage();
		cpuAnalysis.printDiskUsage();
	}

}
