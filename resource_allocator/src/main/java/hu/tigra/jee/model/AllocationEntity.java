/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hu.tigra.jee.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class AllocationEntity extends EqualsById implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /*Manuálisan tesztelve:
    - nem enged üres mezőt
    - nem enged nem email formátumot
     */
    @NotNull
    @NotEmpty
    @Email
    private String email;

    /*Manuálisan tesztelve:
    - nem enged üres mezőt
    - nem enged 20 karakternél többet
     */
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "subject")
    private String subject;

    /*Működik, de nem jó:
    - startDate legyen nagyobb, mint sysDate
    - endDate legyen nagyobb, mint startDate
    - foglalt periódusok kilövése
     */

    @NotNull
    @Pattern(regexp = "(?:201)[6-7]-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3]).([0-5][0-9])", message = "eg. 2016-10-13 17.00")
    @Column(name = "startDate")
    private String startDate;

    @NotNull
    @Pattern(regexp = "(?:201)[6-7]-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3]).([0-5][0-9])", message = "eg. 2016-10-13 17.00")
    @Column(name = "endDate")
    private String endDate;

    /*@NotNull
    @Valid
    @Column(name = "startDate")
    private Date startDate;

    @NotNull
    @Future
    @Column(name = "endDate")
    private Date endDate;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /*public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}*/


}
