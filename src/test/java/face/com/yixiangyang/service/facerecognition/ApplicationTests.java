package face.com.yixiangyang.service.facerecognition;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yixiangyang.bootstrap.Application;
import com.yixiangyang.bootstrap.Base64ImageUtils;
import com.yixiangyang.service.FaceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class ApplicationTests {

	@Resource
	private FaceService faceService;
	
	@Test
	public void contextLoads() {
		String path = "F:\\dd/me.jpg";
		String group = "group_1";
	    String image = Base64ImageUtils.GetImageStrFromPath(path);
	    System.out.println("------------:"+ image);
		System.out.println(faceService.faceDetect(image));
//		System.out.println(" 这个是:"+faceService.faceSearch(image, group));
	}

}
