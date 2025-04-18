package com.cjbbank.transaction.api.model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String defaultMsg;
    private List<String> params;

    public Message(String format, Object... params) {
        this.defaultMsg = MessageFormat.format(format, params);
        this.params = Objects.nonNull(params) ? Arrays.stream(params).map(Object::toString).toList()
                : new ArrayList<>();
    }

    // provide builder method for params from Object...
    public static Message of(String format, Object... params) {
        Message msg = new Message(format, params);
        return msg;

        //下面的code ，当param 里面有 {test} 这样的内容时 会报异常
        /*   return Message.builder().defaultMsg(MessageFormat.format(format, params))
                .params(Objects.nonNull(params) ? Arrays.stream(params).map(p -> {
                    if (Objects.nonNull(p)) {
                        return p.toString();
                    } else {
                        return "";
                    }
                }).toList() : new ArrayList<>()).build();*/
    }

}
