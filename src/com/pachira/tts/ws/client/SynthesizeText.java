
package com.pachira.tts.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>synthesizeText complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="synthesizeText">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param" type="{http://service.ws.voice.pachira.com/}ttsParam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "synthesizeText", propOrder = {
    "param"
})
public class SynthesizeText {

    protected TtsParam param;

    /**
     * 获取param属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TtsParam }
     *     
     */
    public TtsParam getParam() {
        return param;
    }

    /**
     * 设置param属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TtsParam }
     *     
     */
    public void setParam(TtsParam value) {
        this.param = value;
    }

}
