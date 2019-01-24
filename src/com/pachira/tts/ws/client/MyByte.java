
package com.pachira.tts.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>myByte complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="myByte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buff" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="len" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "myByte", propOrder = {
    "buff",
    "len",
    "start"
})
public class MyByte {

    protected byte[] buff;
    protected int len;
    protected int start;

    /**
     * 获取buff属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBuff() {
        return buff;
    }

    /**
     * 设置buff属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBuff(byte[] value) {
        this.buff = value;
    }

    /**
     * 获取len属性的值。
     * 
     */
    public int getLen() {
        return len;
    }

    /**
     * 设置len属性的值。
     * 
     */
    public void setLen(int value) {
        this.len = value;
    }

    /**
     * 获取start属性的值。
     * 
     */
    public int getStart() {
        return start;
    }

    /**
     * 设置start属性的值。
     * 
     */
    public void setStart(int value) {
        this.start = value;
    }

}
