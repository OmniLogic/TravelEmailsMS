<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" >

<head>
    <meta charset="UTH-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>

<div style="padding:0;margin:0">
    <table width="100%" cellspacing="0" cellpadding="0" align="center">
        <tbody>
        <tr>
            <td align="center" valign="middle" style="background-color:#D2CAB8" bgcolor="#f0f0f0">
                <table width="660" cellspacing="0" cellpadding="0" align="center">
                    <tbody>
                    <tr>
                        <td align="center" valign="middle" style="padding:0 0 0">
                            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                                <tbody>
                                <tr>
                                    <td align="center" valign="middle" style="padding-top:10px">
                                        <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td width="400" align="center" valign="middle"
                                                    style="font-family:SangBleu Sunrise;font-size:12px;color:#7F6F50;line-height:20px">
                                                    ${name}, aqui está seu orçamento.
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="25"></td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle" style="padding:0 0"><a> <img
                                                    style="display:block;border:none"
                                                    src="https://traveltaua.blob.core.windows.net/images-web/logo-araxa.png"
                                                    height="88" class="CToWUd"> </a></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" valign="middle" style="padding-top:30px">
                            <table width="660" cellspacing="0" cellpadding="0" align="center"
                                   style="background-color:#ffffff" bgcolor="#FFFFFF">
                                <tbody>
                                <tr>
                                    <td align="center" valign="middle">
                                        <table cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="center" valign="middle">
                                                    <table cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="center" valign="middle"><img
                                                                        src="https://traveltaua.blob.core.windows.net/images-web/background-araxa.png"
                                                                        width="705" style="display:block;border:none;height:299px"
                                                                        class="CToWUd a6T" tabindex="0">
                                                                <div class="a6S" dir="ltr"
                                                                     style="opacity: 0.01; left: 582px; top: 713px;">
                                                                    <div id=":19m"
                                                                         class="T-I J-J5-Ji aQv T-I-ax7 L3 a5q"
                                                                         role="button" tabindex="0"
                                                                         aria-label="Download attachment "
                                                                         data-tooltip-class="a1V"
                                                                         data-tooltip="Download">
                                                                        <div class="akn">
                                                                            <div class="aSK J-J5-Ji aYr"></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr  style="display: flex; justify-content: space-between;padding-top: 5%;">
                                                <td style="padding-left:40px;">
                                                    <hr width="215" height="2" color="#7F6F50">
                                                </td>

                                                <td style="margin-top:-3px;padding-left:50px;padding-right:50px;">
                                                    <img src="https://traveltaua.blob.core.windows.net/images-web/engrenagem_template_email.png" width="100%">

                                                </td>

                                                <td>
                                                    <hr width="226" height="2" color="#7F6F50" style="margin-right: 40px;">
                                                </td>

                                            </tr>
                                            <tr>
                                                <td align="center" valign="middle">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="top"
                                                                style="font-family:SangBleu Sunrise;
                                                                color:#7F6F50;
                                                                font-weight: 400;
                                                                font-size: 31px;
                                                                line-height: 142%;
                                                                text-align: center;
                                                                padding-top:40px">
                                                                Aguardamos você!
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" valign="middle"
                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:30px;font-weight:bold;padding:20px 15px 0">
                                        Opções de acomodação
                                    </td>
                                </tr>
                                <#list listOptions as option>
                                    <tr>
                                        <td align="center" valign="top">
                                            <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                <tbody>
                                                <tr>
                                                    <td align="left" valign="middle" style="padding-top:30px">
                                                        <table width="100%" cellspacing="0" cellpadding="0" align="center">
                                                            <tbody>
                                                            <tr>
                                                                <td align="left" valign="top" width="50%"
                                                                    style="padding-right:20px">
                                                                    <table>
                                                                        <#list option.listItens as item>
                                                                            <tr>
                                                                                <table width="100%" cellspacing="0" cellpadding="0"
                                                                                       align="center">
                                                                                    <tbody>
                                                                                    <tr>
                                                                                        <td align="left" valign="middle"
                                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:22px;font-weight:bold;padding:10px 0;">
                                                                                            Opção
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" valign="middle">
                                                                                            <table width="100%" cellpadding="0"
                                                                                                   cellspacing="0" border="0"
                                                                                                   align="center">
                                                                                                <tbody>
                                                                                                <tr>
                                                                                                    <td align="left" valign="middle"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px;font-weight:bold;padding:10px 0">
                                                                                                        ${item.description}
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td align="left" valign="middle"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:15px;text-transform:uppercase;font-weight:bold;background-color:#f1f1f1;padding:10px">
                                                                                                        ${item.quantity} quarto(s)
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td align="left" valign="middle"
                                                                                                        style="padding:10px">
                                                                                                        <table width="100%"
                                                                                                               cellspacing="0"
                                                                                                               cellpadding="0"
                                                                                                               align="center">
                                                                                                            <tbody>
                                                                                                            <tr>
                                                                                                                <td align="left"
                                                                                                                    valign="middle"
                                                                                                                    width="50%"
                                                                                                                    style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                                                    ${item.adults} Adulto(s) + ${item.childrens} Criança(s)
                                                                                                                </td>
                                                                                                                <td align="right"
                                                                                                                    valign="middle"
                                                                                                                    width="50%"
                                                                                                                    style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                                                    R$ ${item.amount}
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            </tbody>
                                                                                                            <tr>
                                                                                                                <td align="bottom"
                                                                                                                    valign="middle"
                                                                                                                    width="50%"
                                                                                                                    style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px;font-weight:bold">
                                                                                                                    ${item.tariff_name}
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tbody>
                                                                                                            </tbody>
                                                                                                        </table>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </tr>
                                                                        </#list>
                                                                    </table>
                                                                </td>
                                                                <td align="left" valign="middle" width="50%">
                                                                    <table width="100%" cellspacing="0" cellpadding="0"
                                                                           align="center">
                                                                        <tbody>
                                                                        <tr>
                                                                            <td align="left" valign="middle"
                                                                                style="background-color:#f1f1f1;padding:10px">
                                                                                <table width="100%" cellspacing="0"
                                                                                       cellpadding="0" align="center">
                                                                                    <tbody>
                                                                                    <tr>
                                                                                        <td align="left" valign="middle"
                                                                                            style="padding:5px">
                                                                                            <table width="100%"
                                                                                                   cellspacing="0"
                                                                                                   cellpadding="0"
                                                                                                   align="center">
                                                                                                <tbody>
                                                                                                <tr>
                                                                                                    <td align="left"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                                        Total quartos
                                                                                                    </td>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                                        ${option.reservResumeAmountItem}
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" valign="middle"
                                                                                            style="padding:5px">
                                                                                            <table width="100%"
                                                                                                   cellspacing="0"
                                                                                                   cellpadding="0"
                                                                                                   align="center">
                                                                                                <tbody>
                                                                                                <tr>
                                                                                                    <td align="left"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                                        Taxas
                                                                                                    </td>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                                        ${option.reservResumeValTax}
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" valign="middle"
                                                                                            style="padding:10px 5px 5px">
                                                                                            <table width="100%"
                                                                                                   cellspacing="0"
                                                                                                   cellpadding="0"
                                                                                                   align="center">
                                                                                                <tbody>
                                                                                                <tr>
                                                                                                    <td align="left"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-weight:bold;font-size:16px">
                                                                                                        Valor total
                                                                                                    </td>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-weight:bold;font-size:22px">
                                                                                                        ${option.reservResumeAmountTotal}
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" valign="middle"
                                                                                            style="padding:10px 5px 5px">
                                                                                            <table width="100%"
                                                                                                   cellspacing="0"
                                                                                                   cellpadding="0"
                                                                                                   align="center">
                                                                                                <tbody>
                                                                                                <tr>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:14px"></td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:14px">
                                                                                                        Parcele em até 10x
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" valign="middle"
                                                                                            style="padding:20px 5px 5px">
                                                                                            <table width="100%"
                                                                                                   cellspacing="0"
                                                                                                   cellpadding="0"
                                                                                                   align="center">
                                                                                                <tbody>
                                                                                                <tr>
                                                                                                    <td align="center"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:15px;font-weight:bold;background-color:#7F6F50;padding:20px">
                                                                                                        <a href="${option.minhaurl}"
                                                                                                           style="text-decoration:none;color:#ffffff;display:block;text-transform:uppercase"
                                                                                                           target="_blank"
                                                                                                           data-saferedirecturl="https://www.google.com/url?q=https:/reservas.taua.com.br/taua">
                                                                                                            Verificar
                                                                                                            disponibilidade </a>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr style="background-color:#ffffff" bgcolor="#FFFFFF">
                                        <td align="center" valign="top" style="padding-top:40px;padding-bottom:30px"><img
                                                    src="https://ci6.googleusercontent.com/proxy/Zlh_0S53A1zVRWXhzFEfZQNBMaLMTcCqiGRuwhIWLdZc6RdoVnzr--TtBEYPDIxItVvZgcn6wGhUJ87HS22g7CzTeW9PnkL7cRYwyy9rsQuULXIU-U5C_Rl-=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/separador.jpg"
                                                    width="600" alt="Separador" style="margin:0;border:none;display:block"
                                                    class="CToWUd"></td>
                                    </tr>
                                </#list>

                                <tr style="background-color:#ffffff" bgcolor="#FFFFFF">
                                    <td align="center" valign="middle">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="top"
                                                    style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:30px;font-weight:bold">
                                                    Detalhes do orçamento
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left" valign="top">
                                                    <table width="100%" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="top" width="50%"
                                                                style="padding-top:20px">
                                                                <table width="100%" cellspacing="0" cellpadding="0"
                                                                       align="center">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:22px;font-weight:bold;padding-top:10px">
                                                                            Hotel
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px;padding-top:10px">
                                                                            ${hotelName}
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>

                                                        </tr>
                                                        <tr>
                                                            <td align="left" valign="top" width="50%"
                                                                style="padding-top:20px">
                                                                <table width="100%" cellspacing="0" cellpadding="0"
                                                                       align="center">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:22px;font-weight:bold;padding-top:10px">
                                                                            Adultos
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px;padding-top:10px">
                                                                            ${adults}
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                            <td align="left" valign="top" width="50%"
                                                                style="padding-top:20px">
                                                                <table width="100%" cellspacing="0" cellpadding="0"
                                                                       align="center">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:22px;font-weight:bold;padding-top:10px">
                                                                            Crianças
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px;padding-top:10px">
                                                                            ${childrens}
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr style="background-color:#ffffff" bgcolor="#FFFFFF">
                                    <td align="center" valign="top" style="padding-top:40px;padding-bottom:40px"><img
                                                src="https://ci6.googleusercontent.com/proxy/Zlh_0S53A1zVRWXhzFEfZQNBMaLMTcCqiGRuwhIWLdZc6RdoVnzr--TtBEYPDIxItVvZgcn6wGhUJ87HS22g7CzTeW9PnkL7cRYwyy9rsQuULXIU-U5C_Rl-=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/separador.jpg"
                                                width="600" alt="Separador" style="margin:0;border:none;display:block"
                                                class="CToWUd"></td>
                                </tr>
                                <tr style="background-color:#ffffff" bgcolor="#FFFFFF">
                                    <td align="center" valign="middle">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="center" valign="top">
                                                    <table width="300" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="center" valign="top" width="48">
                                                                <table width="48" height="48" cellspacing="0"
                                                                       cellpadding="0" align="center">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td align="center" valign="middle"
                                                                            style="background-color:#7F6F50;border-radius:100%">
                                                                            <img src="https://ci3.googleusercontent.com/proxy/rHQr9mPXhnSoE9xPQ89u8dGgc43jml5X-m8KP4l2sjhaHO9dDgGs1hudgW8b0OD932e3sSrijl8uNWXeaPJPSxRzZ-ZU7rLjwXlyniookeW99mW4LZWYpg=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/icon_02.png"
                                                                                 alt="ícone" width="18" height="25"
                                                                                 style="margin:0;border:none;display:block"
                                                                                 class="CToWUd"></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                            <td align="left" valign="middle" style="padding-left:20px">
                                                                <table cellspacing="0" cellpadding="0" align="left">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:24px;font-weight:bold;padding-bottom:10px">
                                                                            Check-in
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="padding-top:10px">
                                                                            <table cellspacing="0" cellpadding="0"
                                                                                   align="left">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td align="left" valign="middle"
                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                        ${stayDateStart}
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="padding-top:10px">
                                                                            <table cellspacing="0" cellpadding="0"
                                                                                   align="left">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td align="left" valign="middle"
                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                        a partir das 15:00
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                                <td align="center" valign="top">
                                                    <table width="300" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="center" valign="top" width="48">
                                                                <table width="48" height="48" cellspacing="0"
                                                                       cellpadding="0" align="center">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td align="center" valign="middle"
                                                                            style="background-color:#7F6F50;border-radius:100%">
                                                                            <img src="https://ci3.googleusercontent.com/proxy/-XyWPQ8efOsXKPqwwPUS035yv5zAhITpy0u9MeBSgyDilCkMkJEMu2qCoBCnPzU3HDmmOM3guiO4M3hXftkw-kNLo-jhkcrdlKpLa6WCRc9FnH4JUC5ewQ=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/icon_03.png"
                                                                                 alt="ícone" width="18" height="25"
                                                                                 style="margin:0;border:none;display:block"
                                                                                 class="CToWUd"></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                            <td align="left" valign="middle" style="padding-left:20px">
                                                                <table cellspacing="0" cellpadding="0" align="left">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:24px;font-weight:bold;padding-bottom:10px">
                                                                            Check-out
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="padding-top:10px">
                                                                            <table cellspacing="0" cellpadding="0"
                                                                                   align="left">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td align="left" valign="middle"
                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                        ${stayDateEnd}
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="padding-top:10px">
                                                                            <table cellspacing="0" cellpadding="0"
                                                                                   align="left">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td align="left" valign="middle"
                                                                                        style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                                        até as 12:00
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr style="background-color:#ffffff" bgcolor="#FFFFFF">
                                    <td align="center" valign="middle">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="top" style="padding-top:40px">
                                                    <table width="50%" cellspacing="0" cellpadding="0" align="left">
                                                        <tbody>
                                                        <tr>
                                                            <td align="center" valign="top"
                                                                style="font-family:SangBleu Sunrise;color:#ffffff;font-size:15px;font-weight:bold;background-color:#7F6F50;text-transform:uppercase;padding:20px">
                                                                <a href="${minhaurl}"
                                                                   style="text-decoration:none;color:#ffffff;display:block"
                                                                   target="_blank"
                                                                   data-saferedirecturl="https://www.google.com/url?q=https:/reservas.taua.com.br/taua">
                                                                    Verificar disponibilidade </a></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr style="background-color:#ffffff" bgcolor="#FFFFFF">
                                    <td align="center" valign="top" style="padding-top:40px">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="middle"
                                                    style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:24px;font-weight:bold;padding-bottom:10px">
                                                    Prefere falar diretamente conosco?
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left" valign="middle" style="padding-top:10px">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="middle"
                                                                style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px;padding-left:20px">
                                                                Telefone ${number}
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left" valign="middle" style="padding-top:10px">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="middle"
                                                                style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px;padding-left:20px">
                                                                E-mail <a href="mailto:reservas2@taua.com.br" target="_blank" style="text-decoration: none;">${email}</a>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr style="background-color:#ffffff" bgcolor="#FFFFFF">
                                    <td align="center" valign="top" style="padding-top:40px">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="middle"
                                                    style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:24px;font-weight:bold">
                                                    Políticas
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left" valign="middle" style="padding-top:10px">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="middle"
                                                                style="font-family:SangBleu Sunrise;color:#7F6F50;font-size:16px">
                                                                Confira nossas políticas.
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#7F6F50">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <#list listPolicies as policie>
                                                                        <tr>
                                                                            <td align="left" valign="middle"
                                                                                style="margin:0;padding:0;font-family:SangBleu Sunrise;font-size:15px;font-weight:bold">
                                                                                <p style="font-size: 15px;font-weight: bold">${policie.tarife_title}</p>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="left" valign="middle"
                                                                                style="margin:0;padding:0;font-family:SangBleu Sunrise;font-size:12px;text-align:justify;line-height:22px">
                                                                                ${policie.tarife_description}.
                                                                                <br>${policie.tarife_policie}

                                                                            </td>
                                                                        </tr>
                                                                    </#list>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#7F6F50">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:SangBleu Sunrise;font-size:12px;">
                                                                            ${policies}
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#7F6F50">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:SangBleu Sunrise;font-size:15px;font-weight:bold">
                                                                            Viajando acompanhado?
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:SangBleu Sunrise;font-size:12px;text-align:justify;line-height:22px">
                                                                            ${childText}
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="40"></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" valign="middle">
                            <table cellspacing="0" cellpadding="0" align="center">
                                <tbody>
                                <tr>
                                    <td height="55"></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>

</body>

</html>