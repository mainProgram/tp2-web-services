export interface IClasseRequest {
  libelle: string;
  professeursIds: [number] | null;
  matieresIds: [number] | null;
  etudiantsIds: [number] | null;
}
