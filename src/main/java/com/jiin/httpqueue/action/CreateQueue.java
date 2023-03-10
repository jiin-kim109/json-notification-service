package com.jiin.httpqueue.action;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public final class CreateQueue extends ActionResponse {

    @NotBlank
    private String queueName;

    @Attribute
    @Builder.Default
    @Min(value = 0)
    @Max(value = 900)
    @Schema(description = "The length of time, in seconds, for which the delivery of all messages in the queue is delayed. Valid values: An integer from 0 to 900 seconds (15 minutes). Default: 0")
    private int delaySeconds = 0;

    @Attribute
    @Builder.Default
    @Min(value = 1)
    @Max(value = 256)
    @Schema(description = "The limit of how many bytes in KiB a message can contain. Valid values: An integer from 1,024 bytes (1 KiB) to 262,144 bytes (256 KiB). Default: 262,144 (256 KiB)")
    private int maximumMessageSize = 256;

    @Attribute
    @Builder.Default
    @Min(value = 60)
    @Max(value = 1209600)
    @Schema(description = "The length of time, in seconds, retains a message. Valid values: An integer from 60 seconds (1 minute) to 1,209,600 seconds (14 days). Default: 345,600 (4 days)")
    private int messageRetensionPeriod = 345600;

    @Attribute
    @Builder.Default
    @Min(value = 0)
    @Max(value = 20)
    @Schema(description = "The length of time, in seconds, for which a ReceiveMessage action waits for a message to arrive. Valid values: An integer from 0 to 20 (seconds). Default: 0.")
    private int receiveMessageWaitTimeSeconds = 0;
}
