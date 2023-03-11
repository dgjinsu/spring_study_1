package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("long-demo")
    @ResponseBody //리턴값으로 html을 반환하지않고 메세지를 바로 띄울 수 있게 해줌
    public String logDemo(HttpServletRequest request) {
        //MyLogger myLogger = myLoggerProvider.getObject(); //컨테이너를 띄울때 호출되는것이 아닌 http요청이 올때 생성
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestUrl(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
