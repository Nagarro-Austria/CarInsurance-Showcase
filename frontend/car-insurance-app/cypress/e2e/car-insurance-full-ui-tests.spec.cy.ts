import {CarInsurancePage} from "../pages/car-insurance.page";

describe('Create a car insurance quote', () => {
  let page: CarInsurancePage;

  beforeEach(() => {
    page = new CarInsurancePage();
    page.visit();
  });

  describe('check premium', () => {
    describe('for different bonus/malus levels', () => {
      it('with 50% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-0').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with 40% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-4').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with 30% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-6').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with 20% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-7').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with no bonus and no malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with 20% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Malus-10').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with 40% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Malus-13').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with 70% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Malus-15').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
      it('with 100% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Malus-17').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('123');
      });
    });
    describe('for different zip code', () => {
      it('no risk zip code', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('123');
      });
      it('low risk zip code', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '3334').nextStep();

        page.getPremium().contains('123');
      });
      it('high risk zip code', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '9999').nextStep();

        page.getPremium().contains('123');
      });

    });
    describe('for different car power', () => {
      it('low power with fix premium', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 10).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('123');
      });
      it('medium power with % premium', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('123');
      });
      it('high power with fix premium', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 146).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('123');
      });
    });
  });

  describe('check tax', () => {
    describe('for different fuel types', () => {
      it('for Gasoline', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-0').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('10');
      });
      it('for Diesel', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-1').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('10');
      });
      it('for Hybrid', () => {
        page.carDetails('2020-12-01', 'Hybrid', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-2').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('10');
      });
      it('for Electricity', () => {
        page.carDetails('2020-12-01', 'Electricity', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', 'Bonus-3').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('10');
      });
    });
  });

});
