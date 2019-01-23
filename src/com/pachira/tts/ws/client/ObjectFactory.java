
package com.pachira.tts.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.pachira.tts.ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SynthesizeText_QNAME = new QName("http://service.ws.voice.pachira.com/", "synthesizeText");
    private final static QName _SynthesizeTextResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "synthesizeTextResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.pachira.tts.ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SynthesizeText }
     * 
     */
    public SynthesizeText createSynthesizeText() {
        return new SynthesizeText();
    }

    /**
     * Create an instance of {@link SynthesizeTextResponse }
     * 
     */
    public SynthesizeTextResponse createSynthesizeTextResponse() {
        return new SynthesizeTextResponse();
    }

    /**
     * Create an instance of {@link TtsResponse }
     * 
     */
    public TtsResponse createTtsResponse() {
        return new TtsResponse();
    }

    /**
     * Create an instance of {@link TtsParam }
     * 
     */
    public TtsParam createTtsParam() {
        return new TtsParam();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynthesizeText }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "synthesizeText")
    public JAXBElement<SynthesizeText> createSynthesizeText(SynthesizeText value) {
        return new JAXBElement<SynthesizeText>(_SynthesizeText_QNAME, SynthesizeText.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynthesizeTextResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "synthesizeTextResponse")
    public JAXBElement<SynthesizeTextResponse> createSynthesizeTextResponse(SynthesizeTextResponse value) {
        return new JAXBElement<SynthesizeTextResponse>(_SynthesizeTextResponse_QNAME, SynthesizeTextResponse.class, null, value);
    }

}
