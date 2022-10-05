<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" >

<head>
    <meta charset="UTH-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Mail</title>
</head>

<body>

<div style="padding:0;margin:0">
    <table width="100%" cellspacing="0" cellpadding="0" align="center">
        <tbody>
        <tr>
            <td align="center" valign="middle" style="background-color:#f0f0f0" bgcolor="#f0f0f0">
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
                                                    style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#696969;line-height:20px">
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
                                                <td width="660" style="background-color:#00893d">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="top"
                                                                style="padding:30px 0 20px;font-family:Arial,Helvetica,sans-serif;color:#ffffff;font-size:28px;font-weight:bold">
                                                                Olá, ${name}
                                                            </td>
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
                                                            <td align="center" valign="top" width="30">
                                                                <table cellspacing="0" cellpadding="0" align="center">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td width="30" height="220"
                                                                            style="background-color:#00893d"></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                            <td align="center" valign="middle"><img
                                                                        src="${hotelImage}"
                                                                        width="600" style="display:block;border:none"
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
                                                            <td align="center" valign="top" width="30">
                                                                <table cellspacing="0" cellpadding="0" align="center">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td width="30" height="220"
                                                                            style="background-color:#00893d"></td>
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
                                                <td align="center" valign="middle">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="top"
                                                                style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px;line-height:25px;padding-top:40px">
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
                                        style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:30px;font-weight:bold;padding:20px 15px 0">
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
                                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#898989;font-size:22px;font-weight:bold;padding:10px 0">
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
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#00893d;font-size:18px;font-weight:bold;padding:10px 0">
                                                                                                        ${item.description}
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td align="left" valign="middle"
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#303030;font-size:15px;text-transform:uppercase;font-weight:bold;background-color:#f1f1f1;padding:10px">
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
                                                                                                                    style="font-family:Arial,Helvetica,sans-serif;color:#acacac;font-size:16px">
                                                                                                                    ${item.adults} Adulto(s) + ${item.childrens} Criança(s)
                                                                                                                </td>
                                                                                                                <td align="right"
                                                                                                                    valign="middle"
                                                                                                                    width="50%"
                                                                                                                    style="font-family:Arial,Helvetica,sans-serif;color:#acacac;font-size:16px">
                                                                                                                    R$ ${item.amount}
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            </tbody>
                                                                                                            <tr>
                                                                                                                <td align="bottom"
                                                                                                                    valign="middle"
                                                                                                                    width="50%"
                                                                                                                    style="font-family:Arial,Helvetica,sans-serif;color:#000000;font-size:16px;font-weight:bold">
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
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#807f7f;font-size:16px">
                                                                                                        Total quartos
                                                                                                    </td>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#807f7f;font-size:16px">
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
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#807f7f;font-size:16px">
                                                                                                        Taxas
                                                                                                    </td>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#807f7f;font-size:16px">
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
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#00893d;font-weight:bold;font-size:18px">
                                                                                                        Valor total
                                                                                                    </td>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#00893d;font-weight:bold;font-size:22px">
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
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:14px"></td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td align="right"
                                                                                                        valign="middle"
                                                                                                        width="50%"
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:14px">
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
                                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#ffffff;font-size:15px;font-weight:bold;background-color:#00893d;padding:20px">
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
                                    <tr>
                                        <td align="center" valign="top" style="padding-top:40px;padding-bottom:30px"><img
                                                    src="https://ci6.googleusercontent.com/proxy/Zlh_0S53A1zVRWXhzFEfZQNBMaLMTcCqiGRuwhIWLdZc6RdoVnzr--TtBEYPDIxItVvZgcn6wGhUJ87HS22g7CzTeW9PnkL7cRYwyy9rsQuULXIU-U5C_Rl-=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/separador.jpg"
                                                    width="600" alt="Separador" style="margin:0;border:none;display:block"
                                                    class="CToWUd"></td>
                                    </tr>
                                </#list>

                                <tr>
                                    <td align="center" valign="middle">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="top"
                                                    style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:30px;font-weight:bold">
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
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:22px;font-weight:bold;padding-top:10px">
                                                                            Hotel
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px;padding-top:10px">
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
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:22px;font-weight:bold;padding-top:10px">
                                                                            Adultos
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px;padding-top:10px">
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
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:22px;font-weight:bold;padding-top:10px">
                                                                            Crianças
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="top"
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px;padding-top:10px">
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
                                <tr>
                                    <td align="center" valign="top" style="padding-top:40px;padding-bottom:40px"><img
                                                src="https://ci6.googleusercontent.com/proxy/Zlh_0S53A1zVRWXhzFEfZQNBMaLMTcCqiGRuwhIWLdZc6RdoVnzr--TtBEYPDIxItVvZgcn6wGhUJ87HS22g7CzTeW9PnkL7cRYwyy9rsQuULXIU-U5C_Rl-=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/separador.jpg"
                                                width="600" alt="Separador" style="margin:0;border:none;display:block"
                                                class="CToWUd"></td>
                                </tr>
                                <tr>
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
                                                                            style="background-color:#00893d;border-radius:100%">
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
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:24px;font-weight:bold;padding-bottom:10px">
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
                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px">
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
                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px">
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
                                                                            style="background-color:#00893d;border-radius:100%">
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
                                                                            style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:24px;font-weight:bold;padding-bottom:10px">
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
                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px">
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
                                                                                        style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px">
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
                                <tr>
                                    <td align="center" valign="middle">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="top" style="padding-top:40px">
                                                    <table width="50%" cellspacing="0" cellpadding="0" align="left">
                                                        <tbody>
                                                        <tr>
                                                            <td align="center" valign="top"
                                                                style="font-family:Arial,Helvetica,sans-serif;color:#ffffff;font-size:15px;font-weight:bold;background-color:#00893d;text-transform:uppercase;padding:20px">
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
                                <tr>
                                    <td align="center" valign="top" style="padding-top:40px">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="middle"
                                                    style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:24px;font-weight:bold;padding-bottom:10px">
                                                    Prefere falar diretamente conosco?
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left" valign="middle" style="padding-top:10px">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td width="18" align="left" valign="middle"><img
                                                                        src="https://ci6.googleusercontent.com/proxy/Zi7YbEAWbvDjxhcHmzq4478-qYAv6wbay4WXCGspSNfAXfyYaV-73qnyhawQK5v2j5CU-aBbGqUK5WWeyuVjMnw0s9fSE3Orh7O3HHgxr_I1-XVe=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/tel.png"
                                                                        width="18" height="18" alt="Telefone"
                                                                        style="margin:0;border:none;display:block"
                                                                        class="CToWUd"></td>
                                                            <td align="left" valign="middle"
                                                                style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px;padding-left:20px">
                                                                0800 333 1900
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
                                                            <td width="18" align="left" valign="middle"><img
                                                                        src="https://ci3.googleusercontent.com/proxy/LhbckS-0kmDbebXvyFmHvhNMsYjkTztXfLWCwvEPZB77uPsp1xaDqSbxVFNR-y-PJhMDR4i3xtp3vbE8CE4eDKvQLlHYy6WlOTrLb0ZeZCErR6FZ5w=s0-d-e1-ft#https://letsimage.s3.amazonaws.com/Common/Email/orcamento/mail.png"
                                                                        width="18" height="18" alt="E-mail"
                                                                        style="margin:0;border:none;display:block"
                                                                        class="CToWUd"></td>
                                                            <td align="left" valign="middle"
                                                                style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px;padding-left:20px">
                                                                <a href="mailto:reservas2@taua.com.br" target="_blank">reservas2@taua.com.br</a>
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
                                    <td align="center" valign="top" style="padding-top:40px">
                                        <table width="600" cellspacing="0" cellpadding="0" align="center">
                                            <tbody>
                                            <tr>
                                                <td align="left" valign="middle"
                                                    style="font-family:Arial,Helvetica,sans-serif;color:#333333;font-size:24px;font-weight:bold">
                                                    Políticas
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left" valign="middle" style="padding-top:10px">
                                                    <table width="600" cellspacing="0" cellpadding="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                            <td align="left" valign="middle"
                                                                style="font-family:Arial,Helvetica,sans-serif;color:#696969;font-size:18px">
                                                                Confira nossas políticas.
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#696969">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:15px;font-weight:bold">
                                                                            Política do orçamento
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;text-align:justify;line-height:22px">
                                                                            Valores e disponibilidades sujeitos a
                                                                            mudança a qualquer momento, independente do
                                                                            conteúdo apresentado nesse orçamento.
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#696969">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:15px;font-weight:bold">
                                                                            Políticas Gerais
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;text-align:justify;line-height:22px">
                                                                            - Para liberação do check-in, será
                                                                            indispensável que o titular do cartão
                                                                            apresente ao recepcionista o cartão
                                                                            empregado na confirmação da reserva,
                                                                            juntamente com seu RG.- Não nos
                                                                            responsabilizamos por objetos de valor
                                                                            deixados no interior do apartamento.- Não é
                                                                            permitido a entrada de alimentos e bebidas
                                                                            nas dependências do hotel.- As despesas
                                                                            extras serão pagas à vista na saída do hotel
                                                                            em dinheiro ou cartão.Não aceitamos o cartão
                                                                            Hipercard.- Por determinação da Lei Federal
                                                                            n. 8.069, de 13/07/1990, é proibida a
                                                                            hospedagem de menor de idade desacompanhado
                                                                            dos pais, sem o documento que autorize a sua
                                                                            estada. - TAXA de ISS já está inclusa no
                                                                            valor da hospedagem.
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#696969">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <#list listPolicies as policie>
                                                                        <tr>
                                                                            <td align="left" valign="middle"
                                                                                style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:15px;font-weight:bold">
                                                                                <p style="font-size: 15px;font-weight: bold">${policie.tarife_title}</p>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="left" valign="middle"
                                                                                style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;text-align:justify;line-height:22px">
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
                                                                       style="margin:0;padding:0;display:block;color:#696969">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:15px;font-weight:bold">
                                                                            MEDIDAS DE SEGURANÇA
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;text-align:justify;line-height:22px">
                                                                            Obrigatório o uso de máscaras em ambientes
                                                                            de circulação com outros hóspedes; Aferição
                                                                            de temperatura na entrada do resort; Em caso
                                                                            de temperatura superior a 37,5º a reserva
                                                                            será alterada e a entrada não será
                                                                            permitida; Pontos de álcool em gel em locais
                                                                            estratégicos em todo o resort; Rigorosos
                                                                            processos de limpeza e desinfecção com
                                                                            antissépticos ou sanitizantes autorizados
                                                                            pela ANVISA RDC 350/2020;*Troca de roupa de
                                                                            cama realizada por profissional paramentado
                                                                            e procedimentos de transporte sistêmicos; As
                                                                            atividades com Os Taualegres estão sendo
                                                                            realizadas ao ar livre e com turma reduzida
                                                                            de crianças; Para sua segurança alguns
                                                                            ambientes estarão com agendamento de horário
                                                                            ou fechados (consulte no ato da reserva);
                                                                            Para as refeições é obrigatório luvas
                                                                            descartáveis para servir, além do uso
                                                                            máscaras durante a locomoção no restaurante
                                                                            e no momento de se servir, podendo ser
                                                                            retiradas somente na hora da refeição;Nossos
                                                                            colaboradores passam por treinamentos
                                                                            frequentes dos protocolos de segurança.
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#696969">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:15px;font-weight:bold">
                                                                            E se precisar cancelar?
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;text-align:justify;line-height:22px">
                                                                            As condições para cancelamento variam de
                                                                            acordo com o tipo de tarifa. Verifique as
                                                                            políticas de cada condição e tire suas
                                                                            dúvidas.
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#696969">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:15px;font-weight:bold">
                                                                            Formas de pagamento
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;text-align:justify;line-height:22px">
                                                                            A reserva é confirmada mediante pagamento. O
                                                                            valor será debitado no cartão no momento da
                                                                            compra.
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0" cellpadding="0"
                                                                       style="margin:0;padding:0;display:block;color:#696969">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td height="25"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:15px;font-weight:bold">
                                                                            Viajando acompanhado?
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="left" valign="middle"
                                                                            style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;text-align:justify;line-height:22px">
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