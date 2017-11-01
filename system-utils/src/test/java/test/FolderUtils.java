package test;
import java.io.File;

public class FolderUtils {
	
	public static void main(String[] args) {
		readFile("D://images");
	}
	
	
	  public static void readFile(String path){
          File file=new File(path);
          File[] tempList = file.listFiles();
          //tempList.length获取文件夹下所包含的文件或者文件夹的个数
          if(tempList.length>0){
            for(File f : tempList) {
                 //如果是文件，直接打印文件名称
                 if(f.isFile()){
                   String fileName =  f.getName().substring(f.getName().length()-8);
                   File newFile = new File("D://images//image" + File.separator + fileName);
                   f.renameTo(newFile);
                 }
            }
      }
   }

}
