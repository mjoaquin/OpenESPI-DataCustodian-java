package org.energyos.espi.datacustodian.web.customer;

import org.energyos.espi.common.domain.ApplicationInformation;
import org.energyos.espi.common.domain.Routes;
import org.energyos.espi.common.service.ApplicationInformationService;
import org.energyos.espi.common.service.ResourceService;
import org.energyos.espi.datacustodian.utils.URLHelper;
import org.energyos.espi.datacustodian.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(Routes.THIRD_PARTY_LIST)
public class ThirdPartyController extends BaseController {

    @Autowired
    private ApplicationInformationService applicationInformationService;
    
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
    	// note that we are only looking at "THIRD_PARTY" relationships here.
        model.put("applicationInformationList", applicationInformationService.findByKind("THIRD_PARTY"));
        return "/customer/thirdparties/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String selectThirdParty(@RequestParam("Third_party") Long thirdPartyId, @RequestParam("Third_party_URL") String thirdPartyURL) {
        ApplicationInformation applicationInformation = resourceService.findById(thirdPartyId, ApplicationInformation.class);
        return "redirect:" + thirdPartyURL + "?" + URLHelper.newScopeParams(applicationInformation.getScope()) 
        		+ "&DataCustodianID=" + applicationInformation.getDataCustodianId();
    }

    public void setApplicationInformationService(ApplicationInformationService applicationInformationService) {
        this.applicationInformationService = applicationInformationService;
    }
    
    public ApplicationInformationService gettApplicationInformationService(ApplicationInformationService applicationInformationService) {
        return this.applicationInformationService;
    }
    
}
