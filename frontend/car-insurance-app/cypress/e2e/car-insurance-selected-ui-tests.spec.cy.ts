import {CarInsurancePage} from "../pages/car-insurance.page";

describe('Create a car insurance quote', () => {
  let page: CarInsurancePage;

  beforeEach(() => {
    page = new CarInsurancePage();
    page.visit();
  });

  describe('for selected examples', () => {
    it('Basic Level, high location risk, average car', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
      page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '8000').nextStep();

      page.getPremium().contains('123');
    });
    it('Basic Level, no location risk, average car', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
      page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

      page.getPremium().contains('123');
    });
    it('Basic Level, low location risk, average car', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
      page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '4000').nextStep();

      page.getPremium().contains('123');
    });
    it('lowest bonus level', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
      page.contractDetails('2022-01-13', 'Liability', 'Bonus-0').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '4000').nextStep();

      page.getPremium().contains('123');
    });
    it('highest malus level', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
      page.contractDetails('2022-01-13', 'Liability', 'Malus-17').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '4000').nextStep();

      page.getPremium().contains('123');
    });
    it('highest power', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 147).nextStep();
      page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '4000').nextStep();

      page.getPremium().contains('123');
    });
    it('lowest power', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 10).nextStep();
      page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '4000').nextStep();

      page.getPremium().contains('123');
    });
    it('highest insurance premium', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 147).nextStep();
      page.contractDetails('2022-01-13', 'Liability', 'Malus-17').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '9000').nextStep();

      page.getPremium().contains('123');
    });
  });

});