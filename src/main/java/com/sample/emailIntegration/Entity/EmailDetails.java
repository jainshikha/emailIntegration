package com.sample.emailIntegration.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {

    // Class data membersz
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}