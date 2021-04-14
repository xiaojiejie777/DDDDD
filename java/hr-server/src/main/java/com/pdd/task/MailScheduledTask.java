package com.pdd.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pdd.pojo.Employee;
import com.pdd.pojo.MailConstant;
import com.pdd.pojo.MailSendLog;
import com.pdd.service.IEmployeeService;
import com.pdd.service.IMailSendLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 邮件发送定时任务
 */
@Component
public class MailScheduledTask {

    @Autowired
    private IMailSendLogService mailSendLogService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 10秒执行一次邮件发送任务
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask(){
        List<MailSendLog> list = mailSendLogService.list(new QueryWrapper<MailSendLog>()
                .eq("status", 0).lt("tryTime", LocalDateTime.now()));
        list.forEach(mailSendLog -> {
            if (mailSendLog.getCount() >= 3){
                mailSendLogService.update(new UpdateWrapper<MailSendLog>().set("status", 2)
                        .eq("msgId", mailSendLog.getMsgId()));
            }
            mailSendLogService.update(new UpdateWrapper<MailSendLog>().set("count", mailSendLog.getCount() + 1)
                    .set("updateTime", LocalDateTime.now())
                    .set("tryTime", LocalDateTime.now().plusMinutes(MailConstant.MSG_TIMEOUT))
                    .eq("msgId", mailSendLog.getMsgId()));
            Employee emp = employeeService.getEmployee(mailSendLog.getEmpId()).get(0);
            //发送消息
            rabbitTemplate.convertAndSend(MailConstant.MAIL_EXCHANGE_NAME, MailConstant.MAIL_ROUTING_KEY_NAME,
                    emp, new CorrelationData(mailSendLog.getMsgId()));
        });
    }
}
