
package com.pachira.tts.ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>uploadWav complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="uploadWav">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datas" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wavName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isOverwrite" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadWav", propOrder = {
    "datas",
    "wavName",
    "language",
    "isOverwrite"
})
public class UploadWav {

    protected List<byte[]> datas;
    protected String wavName;
    protected String language;
    protected boolean isOverwrite;

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
     * 获取wavName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWavName() {
        return wavName;
    }

    /**
     * 设置wavName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWavName(String value) {
        this.wavName = value;
    }

    /**
     * 获取language属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置language属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * 获取isOverwrite属性的值。
     * 
     */
    public boolean isIsOverwrite() {
        return isOverwrite;
    }

    /**
     * 设置isOverwrite属性的值。
     * 
     */
    public void setIsOverwrite(boolean value) {
        this.isOverwrite = value;
    }

}
