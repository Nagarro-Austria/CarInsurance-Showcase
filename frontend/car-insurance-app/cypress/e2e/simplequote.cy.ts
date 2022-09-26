import {CarInsurancePage} from "../pages/car-insurance.page";

describe('create a simple quote', () => {
  let page: CarInsurancePage;

  beforeEach(() => {
    page = new CarInsurancePage();
    page.visit();
  });

  it('for ..', () => {
    page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
    page.contractDetails('2022-01-13', 'Liability', 'Bonus-0').nextStep();
    page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

    page.getTax().contains('10');
    page.getPremium().contains('123');
  });

});
