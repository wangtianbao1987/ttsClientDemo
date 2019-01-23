
package com.pachira.tts.ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ttsResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ttsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datas" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="err_msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="err_no" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ttsResponse", propOrder = {
    "datas",
    "errMsg",
    "errNo",
    "size",
    "sn"
})
public class TtsResponse {

    @XmlElement(nillable = true)
    protected List<byte[]> datas;
    @XmlElement(name = "err_msg")
    protected String errMsg;
    @XmlElement(name = "err_no")
    protected String errNo;
    protected long size;
    protected String sn;

    /**
     * Gets the value of the datas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * byte[]
     * 
     */
    public List<byte[]> getDatas() {
        if (datas == null) {
            datas = new ArrayList<byte[]>();
        }
        return this.datas;
    }

    /**
     * 获取errMsg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置errMsg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMsg(String value) {
        this.errMsg = value;
    }

    /**
     * 获取errNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrNo() {
        return errNo;
    }

    /**
     * 设置errNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrNo(String value) {
        this.errNo = value;
    }

    /**
     * 获取size属性的值。
     * 
     */
    public long getSize() {
        return size;
    }

    /**
     * 设置size属性的值。
     * 
     */
    public void setSize(long value) {
        this.size = value;
    }

    /**
     * 获取sn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置sn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSn(String value) {
        this.sn = value;
    }

}
