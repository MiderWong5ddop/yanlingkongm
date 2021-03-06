package com.kingdee.controller.kingdeeAction;

import com.kingdee.controller.customer.ArrayOfCustomer;
import com.kingdee.controller.customer.Customer;
import com.kingdee.controller.customer.CustomerService;
import com.kingdee.controller.customer.CustomerServiceSoap;
import com.kingdee.controller.kingdee.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * User: liuc
 * Date: 2017/6/12
 * Time: 10:56
 */
@RestController
@RequestMapping("/system")
@Slf4j
@Api(description = "金蝶凭证")
public class AppUserController {
    //凭证URL
    private static final QName SERVICE_NAME = new QName("http://www.kingdee.com/VoucherData", "VoucherServices");
    //客户增加URL
    private static final QName SERVICE_NAME2 = new QName("http://www.kingdee.com/Customer", "CustomerService");
    @ApiOperation(value = "金蝶凭证录入",
            notes = "金蝶凭证录入",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "incomeMoneya", value = "放款本金", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "afterMoneya", value = "放款本金已还金额", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "incomeMoney", value = "本金", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "afterMoney", value = "本金已还金额", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "afterMoney2", value = "已还利息", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "afterMoney3", value = "已还违约金", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "overdueAfterMoney", value = "已还逾期", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "faxiAfterMoney", value = "已还罚息", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "insuranceMoney", value = "已还保险", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "investigationMoney", value = "已还调查费", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "incomeMoneyas", value = "退费金额", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "afterMoneyas", value = "已还退费金额", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "factDate", value = "实际到账日", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "fullname", value = "当前登录用户名", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "departMentName2", value = "办事处名称", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "kingdeeCode", value = "办事处科目代码", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "客户姓名", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "kingdeeNumber", value = "客户金蝶代码", required = false, paramType = "form", dataType = "String")
    })
    @PostMapping(value = "/kingdee.do")
    public String kingdee(
            @RequestParam (value="incomeMoneya", required = false) String incomeMoneya,
            @RequestParam (value="afterMoneya", required = false) String afterMoneya,
            @RequestParam (value="incomeMoney", required = false) String incomeMoney,
            @RequestParam (value="afterMoney", required = false) String afterMoney,
            @RequestParam (value="afterMoney2", required = false) String afterMoney2,
            @RequestParam (value="afterMoney3", required = false) String afterMoney3,
            @RequestParam (value="overdueAfterMoney", required = false) String overdueAfterMoney,
            @RequestParam (value="faxiAfterMoney", required = false) String faxiAfterMoney,
            @RequestParam (value="insuranceMoney", required = false) String insuranceMoney,
            @RequestParam (value="investigationMoney", required = false) String investigationMoney,
            @RequestParam (value="incomeMoneyas", required = false) String incomeMoneyas,
            @RequestParam (value="afterMoneyas", required = false) String afterMoneyas,
            @RequestParam (value="factDate", required = false) String factDate,
            @RequestParam (value="fullname", required = false) String fullname,
            @RequestParam (value="departMentName2", required = false) String departMentName2,
            @RequestParam (value="kingdeeCode", required = false) String kingdeeCode,
            @RequestParam (value="name", required = false) String name,
            @RequestParam (value="kingdeeNumber", required = false) String kingdeeNumber
    ){
            System.out.print(
                    "---------本金="+incomeMoneya
                    +"本金已还金额=" +afterMoneya
                    +"本金="+incomeMoney
                    +"本金已还金额=" +afterMoney
                    +"已还利息="+afterMoney2
                    +"已还罚息="+faxiAfterMoney
                    +"已还逾期="+overdueAfterMoney
                    +"已还违约金="+afterMoney3
                    +"已还调查费="+investigationMoney
                    +"已还保险="+insuranceMoney
                    +"退费金额="+incomeMoneyas
                    +"已还退费金额=" +afterMoneyas
                    +"实际到账日="+factDate
                    +"当前登录用户名="+fullname
                    +"办事处"+departMentName2
                    +"办事处代码"+kingdeeCode
                    +"借款人代码"+kingdeeNumber
                    +"借款人姓名"+name);

            if(incomeMoneya == null){
                    incomeMoneya = "0";
            }if(afterMoneya == null){
                    afterMoneya = "0";
            }if(incomeMoney == null){
                    incomeMoney = "0";
            }if(afterMoney == null){
                    afterMoney = "0";
            }if(afterMoney2 == null){
                    afterMoney2 = "0";
            }if(afterMoney3 == null){
                    afterMoney3 = "0";
            }if(overdueAfterMoney == null){
                    overdueAfterMoney = "0";
            }if(faxiAfterMoney == null){
                    faxiAfterMoney = "0";
            }if(insuranceMoney == null){
                    insuranceMoney = "0";
            }if(investigationMoney == null){
                    investigationMoney = "0";
            }if(incomeMoneyas == null){
                    incomeMoneyas = "0";
            }if(afterMoneyas == null){
                    afterMoneyas = "0";
            }
            if(afterMoneyas != null && !afterMoneyas.equals("0")){
                  BigDecimal refund = new BigDecimal(0);
                  BigDecimal refund1 = new BigDecimal(0);
                  BigDecimal refund2 = new BigDecimal(afterMoneyas);
                  refund = refund1.subtract(refund2);
                  afterMoneyas =  refund.toString();
            }

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            String str = sdf.format( date );
            if(factDate == null){
                factDate = str;
            }

            //客户账套ID
            int iAisID = 4;
            //客户账户
            String strUser = "administrator";
            String password = "";
            System.out.println("增加客户");
            URL wsdlURL2 = CustomerService.WSDL_LOCATION;
            CustomerService ss2 = new CustomerService(wsdlURL2, SERVICE_NAME2);
            CustomerServiceSoap port2 = ss2.getCustomerServiceSoap();
            ArrayOfCustomer av2 = new ArrayOfCustomer();
            Customer customer = new Customer();
            customer.setNumber(kingdeeNumber);
            customer.setName(name);
            String uuId = UUID.randomUUID().toString();
            customer.setUUID(uuId);
            av2.getCustomer().add(customer);
            int _update_iAisID2 = iAisID;
            String _update_strUser2 = strUser;
            String _update_strPassword2 = password;
            ArrayOfCustomer _update_data2 = av2;
            boolean _update_bCheckByUUID2 = true;
            boolean _update_bAddNewOnly2 = true;
            javax.xml.ws.Holder<Boolean> _update_updateResult2 = new javax.xml.ws.Holder<Boolean>();
            javax.xml.ws.Holder<String> _update_strError2 = new javax.xml.ws.Holder<String>();
            port2.update(_update_iAisID2, _update_strUser2, _update_strPassword2, _update_data2, _update_bCheckByUUID2, _update_bAddNewOnly2, _update_updateResult2, _update_strError2);

            System.out.println("update._update_updateResult2=" + _update_updateResult2.value);
            System.out.println("update._update_strError2=" + _update_strError2.value);








            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy");
            Date dates = new Date();
            String fYear = sdfs.format(dates);

            System.out.println( "添加凭证" );
            URL wsdlURL = VoucherServices.WSDL_LOCATION;
            VoucherServices ss = new VoucherServices( wsdlURL, SERVICE_NAME );
            VoucherServicesSoap port = ss.getVoucherServicesSoap();
            ArrayOfVoucher av = new ArrayOfVoucher();
            Voucher vouchera = new Voucher();
            vouchera.setFDate(str);//日期（日期必须在当前凭证期间） 填写
            vouchera.setFExplanation( "产品入库_调压阀" );//凭证摘要
            vouchera.setFAttachments( 0L );//附件数量    默认0 填写
            vouchera.setFCashier(fullname);//出纳员  默认 none 填写
            vouchera.setFGroup( "转" );//凭证字   记 填写
            vouchera.setFHandler( fullname ); //会计主管   填 ""
            vouchera.setFNumber( 1L );  //凭证号 填写
            vouchera.setFPeriod( 11L );  //期间（凭证期间必须开启）
            vouchera.setFPoster( fullname);  //记账人
            vouchera.setFReference( "" );  //参考信息 填 ""
            vouchera.setFPreparer(fullname); //制单人 填写
            vouchera.setFSerialNum( 180L );   //凭证序号
            vouchera.setFTransDate( str );   //发生日期
            vouchera.setFYear(Long.valueOf(fYear));   //发生日期

            CashFlow cf = new CashFlow();
            cf = null;
            //本金放贷
            Entries entries0 = new Entries();
            entries0.setFAmount(Double.valueOf(incomeMoneya)); //金额   永通需要传的字段信息 填写
            entries0.setFAmountFor(0.00); //外币金额
            entries0.setFCurrencyName( "人民币" ); //币种名称
            entries0.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries0.setFDC( 0L ); //0-贷方,1- 借方 填写
            entries0.detailEntries = null;
            entries0.setFEntryID( 0L ); //分录entryID
            entries0.setFExchangeRate( 0.00 );//汇率  非必录
            entries0.setFExplanation( name+"-借款" );//凭证摘要 填写
            entries0.setFMeasureUnitUUID( "" );//非必录
            entries0.fQuantity = 0.00;
            entries0.setFSettleNo( "" );//非必录
            entries0.setFSettleTypeName( "" );//非必录
            entries0.setFTransNo( "" );//非必录
            entries0.fUnitPrice = 0.00;
            entries0.setFAccountNumber( "1001.02" ); //科目编码 填写
            entries0.setFAccountName( "现金2" ); //科目名称 填写
            entries0.setFMeasureUnit( "" ); //非必录
            //本金放贷2
            Entries entriesa = new Entries();
            DetailEntries detailEntriesa = new DetailEntries();
            DetailEntries detailEntriesaa = new DetailEntries();
            detailEntriesa.setFTypeName("办事处");
            detailEntriesa.setFTypeNumber("000");
            detailEntriesa.setFDetailName(departMentName2);
            detailEntriesa.setFDetailNumber(kingdeeCode);
            detailEntriesaa.setFTypeName("客户");
            detailEntriesaa.setFTypeNumber("001");
            detailEntriesaa.setFDetailName(name);
            detailEntriesaa.setFDetailNumber(kingdeeNumber);
            entriesa.setFAmount( Double.valueOf( afterMoneya ) ); //金额   永通需要传的字段信息
            entriesa.setFAmountFor(0.00); //外币金额
            entriesa.setFCurrencyName( "人民币" ); //币种名称
            entriesa.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entriesa.setFDC( 1L ); //0-贷方,1- 借方
            entriesa.getDetailEntries().add(detailEntriesa);
            entriesa.getDetailEntries().add(detailEntriesaa);
            entriesa.setFEntryID( 0L ); //分录entryID
            entriesa.setFExchangeRate( 0.00 );//汇率  非必录
            entriesa.setFExplanation( name+"-借款" );//凭证摘要
            entriesa.setFMeasureUnitUUID( "" );//非必录
            entriesa.fQuantity = 0.00;
            entriesa.setFSettleNo( "" );//非必录
            entriesa.setFSettleTypeName( "" );//非必录
            entriesa.setFTransNo( "" );//非必录
            entriesa.fUnitPrice = 0.00;
            entriesa.setFAccountNumber( "1103" ); //科目编码
            entriesa.setFAccountName( "贷款" ); //科目名称
            entriesa.setFMeasureUnit( "" ); //非必录
            // 已还本金1
            Entries entries01 = new Entries();
            entries01.setFAmount(Double.valueOf(afterMoney)); //金额   永通需要传的字段信息 填写
            entries01.setFAmountFor(0.00); //外币金额
            entries01.setFCurrencyName( "人民币" ); //币种名称
            entries01.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries01.setFDC( 1L ); //0-贷方,1- 借方 填写
            entries01.detailEntries = null;
            entries01.setFEntryID( 0L ); //分录entryID
            entries01.setFExchangeRate( 0.00 );//汇率  非必录
            entries01.setFExplanation( name+"-还本金" );//凭证摘要 填写
            entries01.setFMeasureUnitUUID( "" );//非必录
            entries01.fQuantity = 0.00;
            entries01.setFSettleNo( "" );//非必录
            entries01.setFSettleTypeName( "" );//非必录
            entries01.setFTransNo( "" );//非必录
            entries01.fUnitPrice = 0.00;
            entries01.setFAccountNumber( "1001.02" ); //科目编码 填写
            entries01.setFAccountName( "现金2" ); //科目名称 填写
            entries01.setFMeasureUnit( "" ); //非必录
            //已还本金2
            Entries entries1 = new Entries();
            DetailEntries detailEntries1 = new DetailEntries();
            DetailEntries detailEntries11 = new DetailEntries();
            detailEntries1.setFTypeName("办事处");
            detailEntries1.setFTypeNumber("000");
            detailEntries1.setFDetailName(departMentName2);
            detailEntries1.setFDetailNumber(kingdeeCode);
            detailEntries11.setFTypeName("客户");
            detailEntries11.setFTypeNumber("001");
            detailEntries11.setFDetailName(name);
            detailEntries11.setFDetailNumber(kingdeeNumber);
            entries1.setFAmount( Double.valueOf( afterMoney ) ); //金额   永通需要传的字段信息
            entries1.setFAmountFor(0.00); //外币金额
            entries1.setFCurrencyName( "人民币" ); //币种名称
            entries1.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries1.setFDC( 0L ); //0-贷方,1- 借方
            entries1.getDetailEntries().add(detailEntries1);
            entries1.getDetailEntries().add(detailEntries11);
            entries1.setFEntryID( 0L ); //分录entryID
            entries1.setFExchangeRate( 0.00 );//汇率  非必录
            entries1.setFExplanation( name+"-还本金" );//凭证摘要
            entries1.setFMeasureUnitUUID( "" );//非必录
            entries1.fQuantity = 0.00;
            entries1.setFSettleNo( "" );//非必录
            entries1.setFSettleTypeName( "" );//非必录
            entries1.setFTransNo( "" );//非必录
            entries1.fUnitPrice = 0.00;
            entries1.setFAccountNumber( "1103" ); //科目编码
            entries1.setFAccountName( "贷款" ); //科目名称
            entries1.setFMeasureUnit( "" ); //非必录
            //利息1
            Entries entries2 = new Entries();
            entries2.setFAmount( Double.valueOf( afterMoney2 ) ); //金额   永通需要传的字段信息
            entries2.setFAmountFor(0.00); //外币金额
            entries2.setFCurrencyName( "人民币" ); //币种名称
            entries2.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries2.setFDC( 1L ); //0-贷方,1- 借方
            entries2.detailEntries = null;
            entries2.setFEntryID( 0L ); //分录entryID
            entries2.setFExchangeRate( 0.00 );//汇率  非必录
            entries2.setFExplanation( name+"-还利息" );//凭证摘要
            entries2.setFMeasureUnitUUID( "" );//非必录
            entries2.fQuantity = 0.00;
            entries2.setFSettleNo( "" );//非必录
            entries2.setFSettleTypeName( "" );//非必录
            entries2.setFTransNo( "" );//非必录
            entries2.fUnitPrice = 0.00;
            entries2.setFAccountNumber( "1001.02" ); //科目编码
            entries2.setFAccountName( "现金2" ); //科目名称
            entries2.setFMeasureUnit( "" ); //非必录
            // 利息2
            Entries entries21 = new Entries();
            DetailEntries detailEntries21 = new DetailEntries();
            detailEntries21.setFTypeName("办事处");
            detailEntries21.setFTypeNumber("000");
            detailEntries21.setFDetailNumber(kingdeeCode);
            detailEntries21.setFDetailName(departMentName2);
            entries21.setFAmount( Double.valueOf( afterMoney2 ) ); //金额   永通需要传的字段信息
            entries21.setFAmountFor(0.00); //外币金额
            entries21.setFCurrencyName( "人民币" ); //币种名称
            entries21.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries21.setFDC( 0L ); //0-贷方,1- 借方
            entries21.getDetailEntries().add(detailEntries21);
            entries21.setFEntryID( 0L ); //分录entryID
            entries21.setFExchangeRate( 0.00 );//汇率  非必录
            entries21.setFExplanation( name+"-还利息" );//凭证摘要
            entries21.setFMeasureUnitUUID( "" );//非必录
            entries21.fQuantity = 0.00;
            entries21.setFSettleNo( "" );//非必录
            entries21.setFSettleTypeName( "" );//非必录
            entries21.setFTransNo( "" );//非必录
            entries21.fUnitPrice = 0.00;
            entries21.setFAccountNumber( "6001" ); //科目编码
            entries21.setFAccountName( "利息收入" ); //科目名称
            entries21.setFMeasureUnit( "" ); //非必录
            //罚息1
            Entries entries3 = new Entries();
            entries3.setFAmount( Double.valueOf( faxiAfterMoney ) ); //金额   永通需要传的字段信息
            entries3.setFAmountFor(0.00); //外币金额
            entries3.setFCurrencyName( "人民币" ); //币种名称
            entries3.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries3.setFDC( 1L ); //0-贷方,1- 借方
            entries3.detailEntries = null;
            entries3.setFEntryID( 0L ); //分录entryID
            entries3.setFExchangeRate( 0.00 );//汇率  非必录
            entries3.setFExplanation( name+"-还罚息" );//凭证摘要
            entries3.setFMeasureUnitUUID( "" );//非必录
            entries3.fQuantity = 0.00;
            entries3.setFSettleNo( "" );//非必录
            entries3.setFSettleTypeName( "" );//非必录
            entries3.setFTransNo( "" );//非必录
            entries3.fUnitPrice = 0.00;
            entries3.setFAccountNumber( "1001.02" ); //科目编码
            entries3.setFAccountName( "现金2" ); //科目名称
            entries3.setFMeasureUnit( "" ); //非必录
            // 罚息2
            Entries entries31 = new Entries();
            DetailEntries detailEntries31 = new DetailEntries();
            detailEntries31.setFTypeName("办事处");
            detailEntries31.setFTypeNumber("000");
            detailEntries31.setFDetailName(departMentName2);
            detailEntries31.setFDetailNumber(kingdeeCode);
            entries31.setFAmount( Double.valueOf( faxiAfterMoney ) ); //金额   永通需要传的字段信息
            entries31.setFAmountFor(0.00); //外币金额
            entries31.setFCurrencyName( "人民币" ); //币种名称
            entries31.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries31.setFDC( 0L ); //0-贷方,1- 借方
            entries31.getDetailEntries().add(detailEntries31);
            entries31.setFEntryID( 0L ); //分录entryID
            entries31.setFExchangeRate( 0.00 );//汇率  非必录
            entries31.setFExplanation( name+"-还罚息" );//凭证摘要
            entries31.setFMeasureUnitUUID( "" );//非必录
            entries31.fQuantity = 0.00;
            entries31.setFSettleNo( "" );//非必录
            entries31.setFSettleTypeName( "" );//非必录
            entries31.setFTransNo( "" );//非必录
            entries31.fUnitPrice = 0.00;
            entries31.setFAccountNumber( "6002.01" ); //科目编码
            entries31.setFAccountName( "罚息" ); //科目名称
            entries31.setFMeasureUnit( "" ); //非必录
            //逾期1
            Entries entries4 = new Entries();
            entries4.setFAmount( Double.valueOf( overdueAfterMoney ) ); //金额   永通需要传的字段信息
            entries4.setFAmountFor(0.00); //外币金额
            entries4.setFCurrencyName( "人民币" ); //币种名称
            entries4.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries4.setFDC( 1L ); //0-贷方,1- 借方
            entries4.detailEntries = null;
            entries4.setFEntryID( 0L ); //分录entryID
            entries4.setFExchangeRate( 0.00 );//汇率  非必录
            entries4.setFExplanation( name+"-还逾期费" );//凭证摘要
            entries4.setFMeasureUnitUUID( "" );//非必录
            entries4.fQuantity = 0.00;
            entries4.setFSettleNo( "" );//非必录
            entries4.setFSettleTypeName( "" );//非必录
            entries4.setFTransNo( "" );//非必录
            entries4.fUnitPrice = 0.00;
            entries4.setFAccountNumber( "1001.02" ); //科目编码
            entries4.setFAccountName( "现金2" ); //科目名称
            entries4.setFMeasureUnit( "" ); //非必录
            // 逾期2
            Entries entries41 = new Entries();
            DetailEntries detailEntries41 = new DetailEntries();
            detailEntries41.setFTypeName("办事处");
            detailEntries41.setFTypeNumber("000");
            detailEntries41.setFDetailName(departMentName2);
            detailEntries41.setFDetailNumber(kingdeeCode);
            entries41.setFAmount( Double.valueOf( overdueAfterMoney ) ); //金额   永通需要传的字段信息
            entries41.setFAmountFor(0.00); //外币金额
            entries41.setFCurrencyName( "人民币" ); //币种名称
            entries41.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries41.setFDC( 0L ); //0-贷方,1- 借方
            entries41.getDetailEntries().add(detailEntries41);
            entries41.setFEntryID( 0L ); //分录entryID
            entries41.setFExchangeRate( 0.00 );//汇率  非必录
            entries41.setFExplanation( name+"-还逾期费" );//凭证摘要
            entries41.setFMeasureUnitUUID( "" );//非必录
            entries41.fQuantity = 0.00;
            entries41.setFSettleNo( "" );//非必录
            entries41.setFSettleTypeName( "" );//非必录
            entries41.setFTransNo( "" );//非必录
            entries41.fUnitPrice = 0.00;
            entries41.setFAccountNumber( "6002.02" ); //科目编码
            entries41.setFAccountName( "逾期费" ); //科目名称
            entries41.setFMeasureUnit( "" ); //非必录
            //违约金1
            Entries entries5 = new Entries();
            entries5.setFAmount( Double.valueOf( afterMoney3 ) ); //金额   永通需要传的字段信息
            entries5.setFAmountFor(0.00); //外币金额
            entries5.setFCurrencyName( "人民币" ); //币种名称
            entries5.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries5.setFDC( 1L ); //0-贷方,1- 借方
            entries5.detailEntries = null;
            entries5.setFEntryID( 0L ); //分录entryID
            entries5.setFExchangeRate( 0.00 );//汇率  非必录
            entries5.setFExplanation( name+"-还违约金" );//凭证摘要
            entries5.setFMeasureUnitUUID( "" );//非必录
            entries5.fQuantity = 0.00;
            entries5.setFSettleNo( "" );//非必录
            entries5.setFSettleTypeName( "" );//非必录
            entries5.setFTransNo( "" );//非必录
            entries5.fUnitPrice = 0.00;
            entries5.setFAccountNumber( "1001.02" ); //科目编码
            entries5.setFAccountName( "现金2" ); //科目名称
            entries5.setFMeasureUnit( "" ); //非必录
            // 违约金2
            Entries entries51 = new Entries();
            DetailEntries detailEntries51 = new DetailEntries();
            detailEntries51.setFTypeName("办事处");
            detailEntries51.setFTypeNumber("000");
            detailEntries51.setFDetailName(departMentName2);
            detailEntries51.setFDetailNumber(kingdeeCode);
            entries51.setFAmount( Double.valueOf( afterMoney3 ) ); //金额   永通需要传的字段信息
            entries51.setFAmountFor(0.00); //外币金额
            entries51.setFCurrencyName( "人民币" ); //币种名称
            entries51.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries51.setFDC( 0L ); //0-贷方,1- 借方
            entries51.getDetailEntries().add(detailEntries51);
            entries51.setFEntryID( 0L ); //分录entryID
            entries51.setFExchangeRate( 0.00 );//汇率  非必录
            entries51.setFExplanation( name+"-还违约金" );//凭证摘要
            entries51.setFMeasureUnitUUID( "" );//非必录
            entries51.fQuantity = 0.00;
            entries51.setFSettleNo( "" );//非必录
            entries51.setFSettleTypeName( "" );//非必录
            entries51.setFTransNo( "" );//非必录
            entries51.fUnitPrice = 0.00;
            entries51.setFAccountNumber( "6002.03" ); //科目编码
            entries51.setFAccountName( "违约金" ); //科目名称
            entries51.setFMeasureUnit( "" ); //非必录
            // 调查费1
            Entries entries6 = new Entries();
            entries6.setFAmount( Double.valueOf( investigationMoney ) ); //金额   永通需要传的字段信息
            entries6.setFAmountFor(0.00); //外币金额
            entries6.setFCurrencyName( "人民币" ); //币种名称
            entries6.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries6.setFDC( 1L ); //0-贷方,1- 借方
            entries6.detailEntries = null;
            entries6.setFEntryID( 0L ); //分录entryID
            entries6.setFExchangeRate( 0.00 );//汇率  非必录
            entries6.setFExplanation( name+"-付调查费" );//凭证摘要
            entries6.setFMeasureUnitUUID( "" );//非必录
            entries6.fQuantity = 0.00;
            entries6.setFSettleNo( "" );//非必录
            entries6.setFSettleTypeName( "" );//非必录
            entries6.setFTransNo( "" );//非必录
            entries6.fUnitPrice = 0.00;
            entries6.setFAccountNumber( "1001.02" ); //科目编码
            entries6.setFAccountName( "现金2" ); //科目名称
            entries6.setFMeasureUnit( "" ); //非必录
            // 调查费2
            Entries entries61 = new Entries();
            DetailEntries detailEntries61 = new DetailEntries();
            detailEntries61.setFTypeName("办事处");
            detailEntries61.setFTypeNumber("000");
            detailEntries61.setFDetailName(departMentName2);
            detailEntries61.setFDetailNumber(kingdeeCode);
            entries61.setFAmount( Double.valueOf( investigationMoney ) ); //金额   永通需要传的字段信息
            entries61.setFAmountFor(0.00); //外币金额
            entries61.setFCurrencyName( "人民币" ); //币种名称
            entries61.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries61.setFDC( 0L ); //0-贷方,1- 借方
            entries61.getDetailEntries().add(detailEntries61);
            entries61.setFEntryID( 0L ); //分录entryID
            entries61.setFExchangeRate( 0.00 );//汇率  非必录
            entries61.setFExplanation( name+"-付调查费" );//凭证摘要
            entries61.setFMeasureUnitUUID( "" );//非必录
            entries61.fQuantity = 0.00;
            entries61.setFSettleNo( "" );//非必录
            entries61.setFSettleTypeName( "" );//非必录
            entries61.setFTransNo( "" );//非必录
            entries61.fUnitPrice = 0.00;
            entries61.setFAccountNumber( "6002.04" ); //科目编码
            entries61.setFAccountName( "调查费" ); //科目名称
            entries61.setFMeasureUnit( "" ); //非必录
            // 保险费1
            Entries entries7 = new Entries();
            entries7.setFAmount( Double.valueOf( insuranceMoney ) ); //金额   永通需要传的字段信息
            entries7.setFAmountFor(0.00); //外币金额
            entries7.setFCurrencyName( "人民币" ); //币种名称
            entries7.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries7.setFDC( 1L ); //0-贷方,1- 借方
            entries7.detailEntries = null;
            entries7.setFEntryID( 0L ); //分录entryID
            entries7.setFExchangeRate( 0.00 );//汇率  非必录
            entries7.setFExplanation( name+"-付保险费" );//凭证摘要
            entries7.setFMeasureUnitUUID( "" );//非必录
            entries7.fQuantity = 0.00;
            entries7.setFSettleNo( "" );//非必录
            entries7.setFSettleTypeName( "" );//非必录
            entries7.setFTransNo( "" );//非必录
            entries7.fUnitPrice = 0.00;
            entries7.setFAccountNumber( "1001.02" ); //科目编码
            entries7.setFAccountName( "现金2" ); //科目名称
            entries7.setFMeasureUnit( "" ); //非必录
            // 保险费2
            Entries entries71 = new Entries();
            DetailEntries detailEntries71 = new DetailEntries();
            detailEntries71.setFTypeName("办事处");
            detailEntries71.setFTypeNumber("000");
            detailEntries71.setFDetailName(departMentName2);
            detailEntries71.setFDetailNumber(kingdeeCode);
            entries71.setFAmount( Double.valueOf( insuranceMoney ) ); //金额   永通需要传的字段信息
            entries71.setFAmountFor(0.00); //外币金额
            entries71.setFCurrencyName( "人民币" ); //币种名称
            entries71.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries71.setFDC( 0L ); //0-贷方,1- 借方
            entries71.getDetailEntries().add(detailEntries71);
            entries71.setFEntryID( 0L ); //分录entryID
            entries71.setFExchangeRate( 0.00 );//汇率  非必录
            entries71.setFExplanation( name+"-付保险费" );//凭证摘要
            entries71.setFMeasureUnitUUID( "" );//非必录
            entries71.fQuantity = 0.00;
            entries71.setFSettleNo( "" );//非必录
            entries71.setFSettleTypeName( "" );//非必录
            entries71.setFTransNo( "" );//非必录
            entries71.fUnitPrice = 0.00;
            entries71.setFAccountNumber( "2203.01" ); //科目编码
            entries71.setFAccountName( "保险费" ); //科目名称
            entries71.setFMeasureUnit( "" ); //非必录


            //退费金额
            Entries entries8 = new Entries();
            entries8.setFAmount( Double.valueOf( afterMoneyas ) ); //金额   永通需要传的字段信息
            entries8.setFAmountFor(0.00); //外币金额
            entries8.setFCurrencyName( "人民币" ); //币种名称
            entries8.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries8.setFDC( 1L ); //0-贷方,1- 借方
            entries8.detailEntries = null;
            entries8.setFEntryID( 0L ); //分录entryID
            entries8.setFExchangeRate( 0.00 );//汇率  非必录
            entries8.setFExplanation( name+"-退费" );//凭证摘要
            entries8.setFMeasureUnitUUID( "" );//非必录
            entries8.fQuantity = 0.00;
            entries8.setFSettleNo( "" );//非必录
            entries8.setFSettleTypeName( "" );//非必录
            entries8.setFTransNo( "" );//非必录
            entries8.fUnitPrice = 0.00;
            entries8.setFAccountNumber( "1001.02" ); //科目编码
            entries8.setFAccountName( "现金2" ); //科目名称
            entries8.setFMeasureUnit( "" ); //非必录
            // 已还退费金额
            Entries entries81 = new Entries();
            DetailEntries detailEntries81 = new DetailEntries();
            detailEntries81.setFTypeName("办事处");
            detailEntries81.setFTypeNumber("000");
            detailEntries81.setFDetailNumber(kingdeeCode);
            detailEntries81.setFDetailName(departMentName2);
            entries81.setFAmount( Double.valueOf( afterMoneyas ) ); //金额   永通需要传的字段信息
            entries81.setFAmountFor(0.00); //外币金额
            entries81.setFCurrencyName( "人民币" ); //币种名称
            entries81.setFCurrencyNumber( "RMB" );  //币种代码     RMB
            entries81.setFDC( 0L ); //0-贷方,1- 借方
            entries81.getDetailEntries().add(detailEntries81);
            entries81.setFEntryID( 0L ); //分录entryID
            entries81.setFExchangeRate( 0.00 );//汇率  非必录
            entries81.setFExplanation( name+"-退费" );//凭证摘要
            entries81.setFMeasureUnitUUID( "" );//非必录
            entries81.fQuantity = 0.00;
            entries81.setFSettleNo( "" );//非必录
            entries81.setFSettleTypeName( "" );//非必录
            entries81.setFTransNo( "" );//非必录
            entries81.fUnitPrice = 0.00;
            entries81.setFAccountNumber( "6001" ); //科目编码
            entries81.setFAccountName( "利息收入" ); //科目名称
            entries81.setFMeasureUnit( "" ); //非必录



            vouchera.getEntries().add(entries0);
            vouchera.getEntries().add(entriesa);
            vouchera.getEntries().add(entries01);
            vouchera.getEntries().add(entries1);
            vouchera.getEntries().add(entries2);
            vouchera.getEntries().add(entries21);
            vouchera.getEntries().add(entries3);
            vouchera.getEntries().add(entries31);
            vouchera.getEntries().add(entries4);
            vouchera.getEntries().add(entries41);
            vouchera.getEntries().add(entries5);
            vouchera.getEntries().add(entries51);
            vouchera.getEntries().add(entries6);
            vouchera.getEntries().add(entries61);
            vouchera.getEntries().add(entries7);
            vouchera.getEntries().add(entries71);
            vouchera.getEntries().add(entries8);
            vouchera.getEntries().add(entries81);
            vouchera.getCashFlow().add(cf);
            av.getVoucher().add(vouchera);




            int _update_iAisID = iAisID;
            java.lang.String _update_strUser = strUser;
            java.lang.String _update_strPassword = password;
            ArrayOfVoucher _update_data = av;
            boolean _update_bCheckByUUID = false;
            boolean _update_bAddNewOnly = false;
            int _update_iBillClassTypeID = 0;
            javax.xml.ws.Holder<java.lang.Boolean> _update_updateResult = new javax.xml.ws.Holder<java.lang.Boolean>();
            javax.xml.ws.Holder<java.lang.String> _update_strError = new javax.xml.ws.Holder<java.lang.String>();
            port.update(_update_iAisID, _update_strUser, _update_strPassword, _update_data, _update_bCheckByUUID, _update_bAddNewOnly, _update_iBillClassTypeID, _update_updateResult, _update_strError);

            System.out.println("update._update_updateResult=" + _update_updateResult.value);
            System.out.println("update._update_strError=" + _update_strError.value);

            return null;
    }
}
