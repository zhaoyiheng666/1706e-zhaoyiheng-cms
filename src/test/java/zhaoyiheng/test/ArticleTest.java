package zhaoyiheng.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.zhaoyiheng.cms.entity.Article;
import com.zhaoyiheng.utils.FileUtils;
/**
 * 
 * @author zhaoyiheng
 *
 */
public class ArticleTest {
	
	/**
	 * 文本读取到数据库
	 * @throws IOException
	 */
	public void testImport() throws IOException{
		File file = new File("D:\\1");
//		获取子目录
		String[] list = file.list();
		for (int i = 0; i < list.length; i++) {
			if(list[i].endsWith(".txt")){
				String content = FileUtils.readFileByLine("D:\\1\\"+list[i]);
				Article article = new Article();
				
			}
		}
	}
	
	@Test
	public void testImportTag() throws IOException{
		List<String> list = FileUtils.readFile("d:\\3.txt");
		for (String string : list) {
			
		}
	}
	
}
