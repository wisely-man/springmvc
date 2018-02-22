package cn.yueworld.config;

import cn.yueworld.core.IBusinessContext;
import cn.yueworld.core.impl.BusinessContext;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * Created by jiangyi on 2017/4/19.
 */
public class BusinessHttpMessageConverter extends AbstractHttpMessageConverter{

    public BusinessHttpMessageConverter(){
        super(new MediaType("application", "wisely", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class aClass) {
        return true;
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        IBusinessContext ctx = new BusinessContext();

        String tempReqStr =
            StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        if(StringUtils.isBlank(tempReqStr)){
            return ctx;
        }

        try {
            tempReqStr = URLDecoder.decode(tempReqStr, "UTF-8");

            String[] params = tempReqStr.split("&");
            for (int i = 0; i < params.length; i++) {
                String[] param = params[i].split("=");
                ctx.setParam(param[0], param[1]);
            }
        }catch (Exception e){
            logger.error("readInputMessage error", e);
        }
        return ctx;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        httpOutputMessage.getBody().write(JSONObject.toJSONString(o).getBytes(Charset.forName("UTF-8")));
    }
}
