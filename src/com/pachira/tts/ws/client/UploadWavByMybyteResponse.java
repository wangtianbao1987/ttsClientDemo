
package com.pachira.tts.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>uploadWavByMybyteResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="uploadWavByMybyteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.ws.voice.pachira.com/}myResp" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadWavByMybyteResponse", propOrder = {
    "_return"
})
public class UploadWavByMybyteResponse {

    @XmlElement(name = "return")
    protected MyResp _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MyResp }
     *     
     */
    public MyResp getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MyResp }
     *     
     */
    public void setReturn(MyResp value) {
        this._return = value;
    }

}
