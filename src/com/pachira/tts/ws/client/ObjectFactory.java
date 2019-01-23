
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

    private final static QName _UploadWav_QNAME = new QName("http://service.ws.voice.pachira.com/", "uploadWav");
    private final static QName _CheckWavNameResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "checkWavNameResponse");
    private final static QName _DeleteWavResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "deleteWavResponse");
    private final static QName _GetWavNamesResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "getWavNamesResponse");
    private final static QName _UploadWavByMybyte_QNAME = new QName("http://service.ws.voice.pachira.com/", "uploadWavByMybyte");
    private final static QName _DeleteWav_QNAME = new QName("http://service.ws.voice.pachira.com/", "deleteWav");
    private final static QName _CheckWavName_QNAME = new QName("http://service.ws.voice.pachira.com/", "checkWavName");
    private final static QName _GetVersion_QNAME = new QName("http://service.ws.voice.pachira.com/", "getVersion");
    private final static QName _SynthesizeText_QNAME = new QName("http://service.ws.voice.pachira.com/", "synthesizeText");
    private final static QName _UploadWavByMybyteResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "uploadWavByMybyteResponse");
    private final static QName _GetWavNames_QNAME = new QName("http://service.ws.voice.pachira.com/", "getWavNames");
    private final static QName _GetVersionResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "getVersionResponse");
    private final static QName _SynthesizeTextResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "synthesizeTextResponse");
    private final static QName _UploadWavResponse_QNAME = new QName("http://service.ws.voice.pachira.com/", "uploadWavResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.pachira.tts.ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MyResp }
     * 
     */
    public MyResp createMyResp() {
        return new MyResp();
    }

    /**
     * Create an instance of {@link MyResp.Data }
     * 
     */
    public MyResp.Data createMyRespData() {
        return new MyResp.Data();
    }

    /**
     * Create an instance of {@link GetVersion }
     * 
     */
    public GetVersion createGetVersion() {
        return new GetVersion();
    }

    /**
     * Create an instance of {@link SynthesizeText }
     * 
     */
    public SynthesizeText createSynthesizeText() {
        return new SynthesizeText();
    }

    /**
     * Create an instance of {@link UploadWavByMybyteResponse }
     * 
     */
    public UploadWavByMybyteResponse createUploadWavByMybyteResponse() {
        return new UploadWavByMybyteResponse();
    }

    /**
     * Create an instance of {@link GetWavNames }
     * 
     */
    public GetWavNames createGetWavNames() {
        return new GetWavNames();
    }

    /**
     * Create an instance of {@link GetVersionResponse }
     * 
     */
    public GetVersionResponse createGetVersionResponse() {
        return new GetVersionResponse();
    }

    /**
     * Create an instance of {@link SynthesizeTextResponse }
     * 
     */
    public SynthesizeTextResponse createSynthesizeTextResponse() {
        return new SynthesizeTextResponse();
    }

    /**
     * Create an instance of {@link UploadWavResponse }
     * 
     */
    public UploadWavResponse createUploadWavResponse() {
        return new UploadWavResponse();
    }

    /**
     * Create an instance of {@link UploadWav }
     * 
     */
    public UploadWav createUploadWav() {
        return new UploadWav();
    }

    /**
     * Create an instance of {@link CheckWavNameResponse }
     * 
     */
    public CheckWavNameResponse createCheckWavNameResponse() {
        return new CheckWavNameResponse();
    }

    /**
     * Create an instance of {@link DeleteWavResponse }
     * 
     */
    public DeleteWavResponse createDeleteWavResponse() {
        return new DeleteWavResponse();
    }

    /**
     * Create an instance of {@link GetWavNamesResponse }
     * 
     */
    public GetWavNamesResponse createGetWavNamesResponse() {
        return new GetWavNamesResponse();
    }

    /**
     * Create an instance of {@link UploadWavByMybyte }
     * 
     */
    public UploadWavByMybyte createUploadWavByMybyte() {
        return new UploadWavByMybyte();
    }

    /**
     * Create an instance of {@link CheckWavName }
     * 
     */
    public CheckWavName createCheckWavName() {
        return new CheckWavName();
    }

    /**
     * Create an instance of {@link DeleteWav }
     * 
     */
    public DeleteWav createDeleteWav() {
        return new DeleteWav();
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
     * Create an instance of {@link MyByte }
     * 
     */
    public MyByte createMyByte() {
        return new MyByte();
    }

    /**
     * Create an instance of {@link MyResp.Data.Entry }
     * 
     */
    public MyResp.Data.Entry createMyRespDataEntry() {
        return new MyResp.Data.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadWav }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "uploadWav")
    public JAXBElement<UploadWav> createUploadWav(UploadWav value) {
        return new JAXBElement<UploadWav>(_UploadWav_QNAME, UploadWav.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckWavNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "checkWavNameResponse")
    public JAXBElement<CheckWavNameResponse> createCheckWavNameResponse(CheckWavNameResponse value) {
        return new JAXBElement<CheckWavNameResponse>(_CheckWavNameResponse_QNAME, CheckWavNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteWavResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "deleteWavResponse")
    public JAXBElement<DeleteWavResponse> createDeleteWavResponse(DeleteWavResponse value) {
        return new JAXBElement<DeleteWavResponse>(_DeleteWavResponse_QNAME, DeleteWavResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWavNamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "getWavNamesResponse")
    public JAXBElement<GetWavNamesResponse> createGetWavNamesResponse(GetWavNamesResponse value) {
        return new JAXBElement<GetWavNamesResponse>(_GetWavNamesResponse_QNAME, GetWavNamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadWavByMybyte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "uploadWavByMybyte")
    public JAXBElement<UploadWavByMybyte> createUploadWavByMybyte(UploadWavByMybyte value) {
        return new JAXBElement<UploadWavByMybyte>(_UploadWavByMybyte_QNAME, UploadWavByMybyte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteWav }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "deleteWav")
    public JAXBElement<DeleteWav> createDeleteWav(DeleteWav value) {
        return new JAXBElement<DeleteWav>(_DeleteWav_QNAME, DeleteWav.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckWavName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "checkWavName")
    public JAXBElement<CheckWavName> createCheckWavName(CheckWavName value) {
        return new JAXBElement<CheckWavName>(_CheckWavName_QNAME, CheckWavName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "getVersion")
    public JAXBElement<GetVersion> createGetVersion(GetVersion value) {
        return new JAXBElement<GetVersion>(_GetVersion_QNAME, GetVersion.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadWavByMybyteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "uploadWavByMybyteResponse")
    public JAXBElement<UploadWavByMybyteResponse> createUploadWavByMybyteResponse(UploadWavByMybyteResponse value) {
        return new JAXBElement<UploadWavByMybyteResponse>(_UploadWavByMybyteResponse_QNAME, UploadWavByMybyteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWavNames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "getWavNames")
    public JAXBElement<GetWavNames> createGetWavNames(GetWavNames value) {
        return new JAXBElement<GetWavNames>(_GetWavNames_QNAME, GetWavNames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "getVersionResponse")
    public JAXBElement<GetVersionResponse> createGetVersionResponse(GetVersionResponse value) {
        return new JAXBElement<GetVersionResponse>(_GetVersionResponse_QNAME, GetVersionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynthesizeTextResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "synthesizeTextResponse")
    public JAXBElement<SynthesizeTextResponse> createSynthesizeTextResponse(SynthesizeTextResponse value) {
        return new JAXBElement<SynthesizeTextResponse>(_SynthesizeTextResponse_QNAME, SynthesizeTextResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadWavResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.voice.pachira.com/", name = "uploadWavResponse")
    public JAXBElement<UploadWavResponse> createUploadWavResponse(UploadWavResponse value) {
        return new JAXBElement<UploadWavResponse>(_UploadWavResponse_QNAME, UploadWavResponse.class, null, value);
    }

}
